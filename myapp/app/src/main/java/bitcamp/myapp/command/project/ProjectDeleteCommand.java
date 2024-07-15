package bitcamp.myapp.command.project;

import bitcamp.myapp.command.Command;
import bitcamp.myapp.vo.Project;
import bitcamp.util.Prompt;
import java.util.List;

public class ProjectDeleteCommand implements Command {

  private List<Project> projectList;

  public ProjectDeleteCommand(List projectList) {
    this.projectList = projectList;
  }

  @Override
  public void execute(String menuName) {
    int projectNo = Prompt.inputInt("프로젝트 번호?");
    Project deletedProject = projectList.get(projectList.indexOf(new Project(projectNo)));
    if (deletedProject != null) {
      projectList.remove(deletedProject);
      System.out.printf("%d번 프로젝트를 삭제 했습니다.\n", deletedProject.getNo());
    } else {
      System.out.println("없는 프로젝트입니다.");
    }
  }


}
