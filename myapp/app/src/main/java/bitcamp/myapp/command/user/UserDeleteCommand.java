package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.List;

public class UserDeleteCommand implements Command {

  private List<User> userList;

  public UserDeleteCommand(List<User> list) {
    this.userList = list;
  }

  @Override
  public void execute(String menuName) {
    int userNo = Prompt.inputInt("회원번호?");
    User deletedUser = (User) userList.get(userList.indexOf(new User(userNo)));
    if (deletedUser != null) {
      userList.remove(userList.indexOf(deletedUser));
      System.out.printf("'%s' 회원을 삭제 했습니다.\n", deletedUser.getName());
    } else {
      System.out.println("없는 회원입니다.");
    }
  }
}
