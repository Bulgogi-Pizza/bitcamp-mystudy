package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImpl implements ProjectDao {

  private Connection con;

  public ProjectDaoImpl(Connection con) {
    this.con = con;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();) {

      StringBuilder members = new StringBuilder();
      for (User user : project.getMembers()) {
        members.append(Integer.toString(user.getNo()));
      }

      // select 문 전달
      stmt.executeUpdate(String.format(
          "insert into myapp_projects(title, description, start_date, end_date, members) "
              + "values('%s', '%s', '%s', '%s', '%s')",
          project.getTitle(),
          project.getDescription(),
          project.getStartDate(),
          project.getEndDate(),
          members));

      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("유저 객체 삽입 중 오류 발생!");
      return false;
    }
  }

  @Override
  public List<Project> list() throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();

        // select 문 실행을 요청한다.
        ResultSet rs = stmt.executeQuery("select * from myapp_projects order by project_id asc")) {

      List<Project> projectList = new ArrayList<>();

      while (rs.next()) { // select 실행 결과에서 1 개의 레코드를 가져온다.
        Project project = new Project();
        project.setNo(rs.getInt("project_id")); // 서버에서 가져온 레코드에서 user_id 컬럼 값을 꺼내 User 객체에 담는다.
        project.setTitle(rs.getString("title"));
        project.setStartDate(rs.getString("start_date"));
        project.setEndDate(rs.getString("end_date"));

        projectList.add(project);
      }

      return projectList;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("유저 객체 호출 중 오류 발생!");
      return null;
    }
  }

  @Override
  public Project findBy(int no) throws Exception {
    try (// SQL을 서버에 전달할 객체 생성
        Statement stmt = con.createStatement();

        // select 문 실행을 요청한다.
        ResultSet rs = stmt.executeQuery("select * from myapp_projects where project_id=" + no)) {

      if (rs.next()) { // select 실행 결과에서 1 개의 레코드를 가져온다.
        Project project = new Project();
        project.setNo(rs.getInt("project_id")); // 서버에서 가져온 레코드에서 user_id 컬럼 값을 꺼내 User 객체에 담는다.
        project.setTitle(rs.getString("name"));
        project.setDescription(rs.getString("email"));
        project.setStartDate(rs.getString("tel"));
        project.setEndDate(rs.getString("end_date"));

        String members = rs.getString("members");
        for (String userNo : members.split(",")) {
          ResultSet rs2 = stmt.executeQuery("select * from myapp_users where user_id=" + userNo);
          User user = new User();
          user.setNo(rs2.getInt("user_id"));
          user.setName(rs2.getString("user_id"));
          user.setEmail(rs2.getString("user_id"));
          user.setPassword(rs2.getString("user_id"));
          user.setTel(rs2.getString("user_id"));
        }
        project.set(rs.getString("end_date"));

        return project;
      }

      return null;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("유저 객체 호출 중 오류 발생!");
      return null;
    }
  }

  @Override
  public boolean update(Project project) throws Exception {
    return false;
  }

  @Override
  public boolean delete(int no) throws Exception {
    return false;
  }
}
