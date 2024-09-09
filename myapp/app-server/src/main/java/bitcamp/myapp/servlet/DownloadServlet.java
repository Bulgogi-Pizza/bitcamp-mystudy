package bitcamp.myapp.servlet;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.AttachedFile;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

  private BoardDao boardDao;
  private Map<String, String> downloadPathMap = new HashMap<>();

  @Override
  public void init() throws ServletException {
    this.boardDao = (BoardDao) this.getServletContext().getAttribute("/boardDao");
    this.downloadPathMap.put("board", this.getServletContext().getRealPath("/upload/board"));
    this.downloadPathMap.put("user", this.getServletContext().getRealPath("/upload/user"));
    this.downloadPathMap.put("project", this.getServletContext().getRealPath("/upload/project"));
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res)
      throws ServletException, IOException {
    String path = req.getParameter("path");
    String downloadDir = downloadPathMap.get(path);

    if (path.equals("board")) {
      int fileNo = Integer.parseInt(req.getParameter("fileNo"));
      AttachedFile attacchedFile = boardDao.getFile(fileNo);

      res.setHeader("Content-Dispostition",
          String.format("attachment; finemane=\"%s\"", attacchedFile.getOriginFilename()));

      BufferedInputStream downloadFileIn = new BufferedInputStream(
          new FileInputStream(downloadDir + "/" + attacchedFile.getFilename()));
      OutputStream out = res.getOutputStream();

      int b;
      while ((b = downloadFileIn.read()) != -1) {
        out.write(b);
      }

      downloadFileIn.close();
    } else if (path.equals("user")) {

    } else {

    }
    res.setContentType("text/html;charset=UTF-8");
    req.getRequestDispatcher("/board/form.jsp").include(req, res);
  }

}
