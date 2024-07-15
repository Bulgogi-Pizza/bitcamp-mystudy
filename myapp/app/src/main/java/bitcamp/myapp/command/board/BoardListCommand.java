package bitcamp.myapp.command.board;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Board;
import java.util.Iterator;
import java.util.List;

public class BoardListCommand implements Command {

  private List<Board> boardList;

  public BoardListCommand(List<Board> list) {
    this.boardList = list;
  }

  @Override
  public void execute(String menuName) {
    System.out.println("번호 제목 작성일 조회수");
    Iterator iterator = boardList.iterator();
    while (iterator.hasNext()) {
      Board board = (Board) iterator.next();
      System.out.printf("%d %s %tY-%3$tm-%3$td %d\n",
          board.getNo(), board.getTitle(), board.getCreatedDate(), board.getViewCount());
    }
  }

}
