package bitcamp.myapp.command.user;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.List;

public class UserViewCommand implements Command {

  private List<User> userList;

  public UserViewCommand(List<User> list) {
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

    System.out.printf("이름: %s\n", user.getName());
    System.out.printf("이메일: %s\n", user.getEmail());
    System.out.printf("연락처: %s\n", user.getTel());
  }
}
