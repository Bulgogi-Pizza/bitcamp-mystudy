package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import java.util.Iterator;
import java.util.List;

public class ProjectViewCommand implements Command {

  private final List<Project> projectList;

  public ProjectViewCommand(List<Project> projectList) {
    this.projectList = projectList;
  }

  @Override
  public void execute(String menuName) {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project project = projectList.get(projectList.indexOf(new Project(projectNo)));
    if (project == null) {
      System.out.println("없는 프로젝트입니다.");
      return;
    }

    System.out.printf("프로젝트명: %s\n", project.getTitle());
    System.out.printf("설명: %s\n", project.getDescription());
    System.out.printf("기간: %s ~ %s\n", project.getStartDate(), project.getEndDate());
    System.out.println("팀원:");
    Iterator memberIterator = projectList.iterator();
    while (memberIterator.hasNext()) {
      User user = (User) memberIterator.next();
      System.out.printf("- %s\n", user.getName());
    }
  }
}
