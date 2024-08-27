package bitcamp.myapp.servlet.board;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/board/list")
public class BoardListServlet implements Servlet {

  private BoardDao boardDao;
  private ServletConfig config;

  @Override
  public void init(ServletConfig config) throws ServletException {
    this.config = config;
    ServletContext ctx = config.getServletContext();
    boardDao = (BoardDao) ctx.getAttribute("boardDao");
  }

  @Override
  public ServletConfig getServletConfig() {
    return this.config;
  }

  @Override
  public void service(ServletRequest req, ServletResponse res)
      throws ServletException, IOException {

    res.setContentType("text/plain;charset=UTF-8");

    PrintWriter out = res.getWriter();

    try {
      out.printf("[게시판 목록]\n");
      out.println("번호 제목 작성자 작성일 조회수");

      for (Board board : boardDao.list()) {
        out.printf("%d %s %s %tY-%4$tm-%4$td %d\n",
            board.getNo(),
            board.getTitle(),
            board.getWriter().getName(),
            board.getCreatedDate(),
            board.getViewCount());
      }
    } catch (Exception e) {
      out.println("목록 조회 중 오류 발생!");
      e.printStackTrace();
    }

  }

  @Override
  public String getServletInfo() {
    return "게시판 목록 조회";
  }

  @Override
  public void destroy() {

  }
}
