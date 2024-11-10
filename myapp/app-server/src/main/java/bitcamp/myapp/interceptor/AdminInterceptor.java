package bitcamp.myapp.interceptor;

import bitcamp.myapp.vo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class AdminInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HttpSession session = request.getSession();
    User loginUser = (User) session.getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/app/auth/form");
      return false;
    }

    if (!loginUser.getName().equals("admin")) {
      throw new Exception("관리자 권한이 필요합니다.");
    }

    return true;
  }
}