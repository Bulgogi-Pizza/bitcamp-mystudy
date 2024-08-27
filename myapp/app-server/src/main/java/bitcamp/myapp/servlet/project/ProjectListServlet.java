package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/project/list")
public class ProjectListServlet implements Servlet {

  private ProjectDao projectDao;
  private ServletConfig config;


  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
    ServletContext ctx = config.getServletContext();
    projectDao = (ProjectDao) ctx.getAttribute("projectDao");
  }

  @Override
  public ServletConfig getServletConfig() {
    return null;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/html;charset=UTF-8");

    // 출력 컨텐트를 어떤 문자집합으로 인코딩 할 지 지정하지 않고 출력 스트림을 꺼내면
    // 출력 문자열(UTF-16BE)은 ISO-8859-1 문자집합에 맞춰 인코딩된다.
    // 만약 UTF-16BE에 있는 문자가 ISO-8859-1에 정의되어 있지 않다면,
    // '?' 문자로 변환된다.

    PrintWriter out = res.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>Title</title>");
    out.println("</head>");
    out.println("<body>");

    out.printf("<h3>[프로젝트 목록]<br></h3>");
    out.println("번호 프로젝트 기간<br>");

    try {
      for (Project project : projectDao.list()) {
        out.printf("%d %s %s ~ %s<br>",
            project.getNo(), project.getTitle(), project.getStartDate(), project.getEndDate());
      }
    } catch (Exception e) {
      out.println("목록 조회 중 오류 발생!");
    }

    out.println("</body>");
    out.println("</html>");

  }

  @Override
  public String getServletInfo() {
    return "프로젝트 목록 조회";
  }

  @Override
  public void destroy() {

  }
}
