package bitcamp.myapp.servlet.project;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/project/add")
public class ProjectAddServlet extends HttpServlet {

  private UserDao userDao;
  private ProjectDao projectDao;
  private SqlSessionFactory sqlSessionFactory;

  @Override
  public void init() throws ServletException {
    ServletContext ctx = this.getServletContext();
    this.projectDao = (ProjectDao) ctx.getAttribute("projectDao");
    this.userDao = (UserDao) ctx.getAttribute("userDao");
    this.sqlSessionFactory = (SqlSessionFactory) ctx.getAttribute("sqlSessionFactory");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      List<User> users = userDao.list();
      req.setAttribute("users", users);

      res.setContentType("text/html;charset=UTF-8");
      req.getRequestDispatcher("/project/form.jsp").include(req, res);

    } catch (Exception e) {
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    try {
      Project project = new Project();
      project.setTitle(req.getParameter("title"));
      project.setDescription(req.getParameter("description"));
      project.setStartDate(Date.valueOf(req.getParameter("startDate")));
      project.setEndDate(Date.valueOf(req.getParameter("endDate")));

      String[] memberNos = req.getParameterValues("member");
      if (memberNos != null) {
        ArrayList<User> members = new ArrayList<>();
        for (String memberNo : memberNos) {
          members.add(new User(Integer.parseInt(memberNo)));
        }
        project.setMembers(members);
      }

      projectDao.insert(project);

      if (project.getMembers() != null && project.getMembers().size() > 0) {
        projectDao.insertMembers(project.getNo(), project.getMembers());
      }
      sqlSessionFactory.openSession(false).commit();
      ((HttpServletResponse) res).sendRedirect("/project/list");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      req.setAttribute("exception", e);
      req.getRequestDispatcher("/error.jsp").forward(req, res);
    }
  }
}
