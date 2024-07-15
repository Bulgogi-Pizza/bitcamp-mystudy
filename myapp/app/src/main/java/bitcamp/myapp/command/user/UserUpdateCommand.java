package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.List;

public class UserUpdateCommand implements Command {

  private List<User> userList;

  public UserUpdateCommand(List<User> list) {
    this.userList = list;
  }

  @Override
  public void execute(String menuName) {
    int userNo = Prompt.inputInt("회원번호?");
    User user = (User) userList.get(userList.indexOf(new User(userNo)));
    if (user == null) {
      System.out.println("없는 회원입니다.");
      return;
    }

    user.setName(Prompt.input("이름(%s)?", user.getName()));
    user.setEmail(Prompt.input("이메일(%s)?", user.getEmail()));
    user.setPassword(Prompt.input("암호?"));
    user.setTel(Prompt.input("연락처(%s)?", user.getTel()));
    System.out.println("변경 했습니다.");
  }
}
