package bitcamp.myapp.servlet;

import bitcamp.myapp.annotation.RequestMapping;
import bitcamp.myapp.annotation.RequestParam;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@MultipartConfig(
    maxFileSize = 1024 * 1024 * 60,
    maxRequestSize = 1024 * 1024 * 100)
@WebServlet("/app/*")
public class DispatcherServlet extends HttpServlet {

  private List<Object> controllers;

  @Override
  public void init() throws ServletException {
    controllers = (List<Object>) this.getServletContext().getAttribute("controllers");
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      // 클라이언트가 요청한 URL을 가지고 페이지 컨트롤러와 요청핸들러(메서드)를 찾는다.
      String controllerPath = req.getPathInfo();

      Object pageController = null;
      Method requestHandler = null;

      loop:
      for (Object controller : controllers) {
        Method[] methods = controller.getClass().getDeclaredMethods();
        for (Method m : methods) {
          RequestMapping requestMapping = m.getAnnotation(RequestMapping.class);
          if (requestMapping == null || !requestMapping.value().equals(controllerPath)) {
            continue;
          }
          requestHandler = m;
          pageController = controller;
          break loop;
        }
      }

      if (pageController == null) {
        throw new Exception("해당 URL을 처리할 수 없습니다.");
      }

      if (requestHandler.getReturnType() == void.class) {
        requestHandler.invoke(pageController, req, res);
        return;
      }

      Map<String, Object> map = new HashMap<>();

      // requestHandler에게 전달할 아규먼트 준비
      // 아래의 방식처럼 낱개로 줘도 되지만,
      // Object[] arguments = prepareMethodArguments(requestHandler, req, res);
      // 가변파라미터는 배열에 담아서 주어도 된다. 그리고 이것이 더 다양한 메소드 호출에 대응하기 편해보인다.
      Object[] arguments = prepareRequestHandlerArguments(requestHandler, req, res, map);

      String viewName = (String) requestHandler.invoke(pageController, arguments);

      // 페이지 컨트롤러가 requestAttrivutesMap에 저장해둔 값을 꺼내 ServletRequest에 담는다.
      if (map.size() > 0) {
        copyMapToServletRequest(map, req);
      }

      // 페이지 컨트롤러가 정상적으로 실행했으면, viewName을 가져와서 포워딩 한다.
      if (viewName.startsWith("redirect:")) {
        res.sendRedirect(viewName.substring(9));

      } else {
        req.getRequestDispatcher(viewName).forward(req, res);
      }

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }

  private Object[] prepareRequestHandlerArguments(
      Method requestHandler,
      HttpServletRequest req,
      HttpServletResponse res,
      Map<String, Object> requestAttributesMap) throws Exception {

    // 메서드의 파라미터 분석
    Parameter[] params = requestHandler.getParameters();
    ArrayList<Object> args = new ArrayList<>();

    for (Parameter param : params) {
      Class<?> paramType = param.getType();
      if (paramType == ServletRequest.class || paramType == HttpServletRequest.class) {
        args.add(req);
      } else if (paramType == ServletResponse.class || paramType == HttpServletResponse.class) {
        args.add(res);
      } else if (paramType == Map.class) {
        args.add(requestAttributesMap);
      } else if (paramType.isPrimitive() || paramType == String.class) {
        RequestParam paramAnno = param.getAnnotation(RequestParam.class);
        args.add(getPrimitiveValueOrStringFromRequestParameter(
            req, // 클라이언트가 보낸 값이 저장된 ServletRequest 보관소
            param.getType(), // ServletRequest 보관소에서 꺼낸 값을 형변환할 때 타입
            paramAnno.value() // ServletRequest 보관소에서 꺼낼 값의 파라미터명
        ));
      } else {
        args.add(createDomainObject(req, param.getType()));
      }
    }

    return args.toArray();

  }

  private void copyMapToServletRequest(Map<String, Object> map, ServletRequest req) {
    for (Entry<String, Object> entry : map.entrySet()) {
      req.setAttribute(entry.getKey(), entry.getValue());
    }
  }

  private Object getPrimitiveValueOrStringFromRequestParameter(
      HttpServletRequest req,
      Class<?> paramType,
      String paramName) {
    String paramValue = req.getParameter(paramName);
    if (paramValue == null) {
      return null;
    }

    if (paramType == byte.class) {
      return Byte.parseByte(paramValue);
    } else if (paramType == short.class) {
      return Short.parseShort(paramValue);
    } else if (paramType == int.class) {
      return Integer.parseInt(paramValue);
    } else if (paramType == Long.class) {
      return Long.parseLong(paramValue);
    } else if (paramType == float.class) {
      return Float.parseFloat(paramValue);
    } else if (paramType == double.class) {
      return Double.parseDouble(paramValue);
    } else if (paramType == char.class) {
      return paramValue.charAt(0);
    } else if (paramType == boolean.class) {
      return Boolean.parseBoolean(paramValue);
    } else {
      return paramValue;
    }
  }

  private Object createDomainObject(HttpServletRequest req, Class<?> paramType)
      throws Exception {
    // 요청 핸들러가 원하는
    Object domainObject = paramType.getConstructor().newInstance();

    // 도메인 객체의 세터 메서드를 호출하여 클라이언트가 보낸 값을 보관한다.
    Method[] methods = paramType.getDeclaredMethods();
    for (Method m : methods) {
      if (Modifier.isPublic(m.getModifiers()) || !m.getName().startsWith("set")) {
        continue;
      }

      Class<?> propertyType = m.getParameterTypes()[0]; // setter 메소드에 파라미터가 한 개 있다고 가정.
      String propertyName = Character.toLowerCase(m.getName().charAt(3)) + m.getName().substring(4);

      // 세터 메서드의 이름(프로퍼티명)과 일치하는 값을 클라이언트가 보낸 파라미터에서 꺼낸다.
      Object value = getPrimitiveValueOrStringFromRequestParameter(req, propertyType,
          paramType.getName());
      if (value == null) { // 세터 메서드에 넣을 값이 없으면
        continue;
      }

      // 세터 메서드에 넣을 값이 클라이언트가 보낸 파라미터에 있으면 객체에 보관한다.
      m.invoke(domainObject, value);
    }

    // 클라이언트가 보낸 값을 보관한 도메인 객체를 리턴한다.
    return domainObject;
  }

}