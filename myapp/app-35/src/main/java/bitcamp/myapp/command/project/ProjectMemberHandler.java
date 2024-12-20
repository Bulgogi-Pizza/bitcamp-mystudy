package bitcamp.myapp.command.project;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;

public class ProjectMemberHandler {

  private UserDao userDao;

  public ProjectMemberHandler(UserDao userDao) {
    this.userDao = userDao;
  }

  public User getMember(int no) throws Exception {
    return userDao.findBy(no);
  }

  public void addMembers(Project project) {
    while (true) {
      int userNo = Prompt.inputInt("추가할 팀원 번호?(종료: 0)");
      if (userNo == 0) {
        break;
      }

      try {
        User user = userDao.findBy(userNo);
        if (user == null) {
          System.out.println("없는 팀원입니다.");
          continue;
        }

        if (project.getMembers().contains(user)) {
          System.out.printf("'%s'은 현재 팀원입니다.\n", user.getName());
          continue;
        }

        project.getMembers().add(user);

        System.out.printf("'%s'을 추가했습니다.\n", user.getName());
      } catch (Exception e) {
        System.out.println("팀원 추가 중 오류 발생!");
      }
    }
  }

  public void deleteMembers(Project project) {
    Object[] members = project.getMembers().toArray();
    for (Object obj : members) {
      User member = (User) obj;

      String str = Prompt.input("팀원(%s) 삭제?", member.getName());

      if (str.equalsIgnoreCase("y")) {
        project.getMembers().remove(member);
        System.out.printf("'%s' 팀원을 삭제합니다.\n", member.getName());
      } else {
        System.out.printf("'%s' 팀원을 유지합니다.\n", member.getName());
      }
    }
  }
}
