package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BoardDaoImpl implements BoardDao {

  private Connection con;

  public BoardDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public boolean insert(Board board) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();) {

      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

      // select 문 전달
      stmt.executeUpdate(String.format(
          "insert into myapp_boards(title, content, created_date) "
              + "values('%s', '%s', '%s')",
          board.getTitle(),
          board.getContent(),
          dateFormat.format(board.getCreatedDate())));

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("게시판 객체 삽입 중 오류 발생!");
      return false;
    }
  }

  @Override
  public List<Board> list() throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();

        // select 문 실행을 요청한다.
        ResultSet rs = stmt.executeQuery("select * from myapp_boards order by board_id asc")) {

      List<Board> boardList = new ArrayList<>();

      while (rs.next()) { // select 실행 결과에서 1 개의 레코드를 가져온다.
        Board board = new Board();
        board.setNo(rs.getInt("board_id")); // 서버에서 가져온 레코드에서 user_id 컬럼 값을 꺼내 User 객체에 담는다.
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setCreatedDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));

        boardList.add(board);
      }

      return boardList;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("게시판 객체 호출 중 오류 발생!");
      return null;
    }
  }

  @Override
  public Board findBy(int no) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();

        // select 문 실행을 요청한다.
        ResultSet rs = stmt.executeQuery("select * from myapp_boards where board_id=" + no)) {

      if (rs.next()) { // select 실행 결과에서 1 개의 레코드를 가져온다.
        Board board = new Board();
        board.setNo(rs.getInt("board_id")); // 서버에서 가져온 레코드에서 user_id 컬럼 값을 꺼내 User 객체에 담는다.
        board.setTitle(rs.getString("title"));
        board.setContent(rs.getString("content"));
        board.setCreatedDate(rs.getDate("created_date"));
        board.setViewCount(rs.getInt("view_count"));

        stmt.executeUpdate(String.format("update myapp_boards "
                + "set view_count=%d where board_id=%d",
            board.getViewCount() + 1,
            board.getNo()));

        return board;
      }

      return null;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("유저 객체 호출 중 오류 발생!");
      return null;
    }
  }

  @Override
  public boolean update(Board board) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();) {

      // select 문 전달
      stmt.executeUpdate(String.format(
          "update myapp_boards set"
              + " title='%s',"
              + " content='%s',"
              + " view_count='%d',"
              + " where board_id=%d",
          board.getTitle(),
          board.getContent(),
          board.getViewCount(),
          board.getNo()));

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("유저 객체 호출 중 오류 발생!");
      return false;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();) {

      int count = stmt.executeUpdate(
          String.format("delete from myapp_boards where board_id=%d", no));

      return count > 0;
    }
  }
}
