package bitcamp.myapp;

import bitcamp.menu.MenuGroup;
import bitcamp.menu.MenuItem;
import bitcamp.myapp.command.HelpCommand;
import bitcamp.myapp.command.HistoryCommand;
import bitcamp.myapp.command.board.BoardAddCommand;
import bitcamp.myapp.command.board.BoardDeleteCommand;
import bitcamp.myapp.command.board.BoardListCommand;
import bitcamp.myapp.command.board.BoardUpdateCommand;
import bitcamp.myapp.command.board.BoardViewCommand;
import bitcamp.myapp.command.project.ProjectAddCommand;
import bitcamp.myapp.command.project.ProjectDeleteCommand;
import bitcamp.myapp.command.project.ProjectListCommand;
import bitcamp.myapp.command.project.ProjectMemberHandler;
import bitcamp.myapp.command.project.ProjectUpdateCommand;
import bitcamp.myapp.command.project.ProjectViewCommand;
import bitcamp.myapp.command.user.UserAddCommand;
import bitcamp.myapp.command.user.UserDeleteCommand;
import bitcamp.myapp.command.user.UserListCommand;
import bitcamp.myapp.command.user.UserUpdateCommand;
import bitcamp.myapp.command.user.UserViewCommand;
import bitcamp.myapp.vo.Board;
import bitcamp.myapp.vo.Project;
import bitcamp.myapp.vo.SequenceNo;
import bitcamp.myapp.vo.User;
import bitcamp.util.Prompt;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class App {


  MenuGroup mainMenu = new MenuGroup("메인");

  List<User> userList = new ArrayList<>();
  List<Project> projectList = new LinkedList<>();
  List<Board> boardList = new LinkedList<>();

  public App() {

    loadData();

    MenuGroup userMenu = new MenuGroup("회원");
    userMenu.add(new MenuItem("등록", new UserAddCommand(userList)));
    userMenu.add(new MenuItem("목록", new UserListCommand(userList)));
    userMenu.add(new MenuItem("조회", new UserViewCommand(userList)));
    userMenu.add(new MenuItem("변경", new UserUpdateCommand(userList)));
    userMenu.add(new MenuItem("삭제", new UserDeleteCommand(userList)));
    mainMenu.add(userMenu);

    MenuGroup projectMenu = new MenuGroup("프로젝트");
    ProjectMemberHandler memberHandler = new ProjectMemberHandler(userList);
    projectMenu.add(new MenuItem("등록", new ProjectAddCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("목록", new ProjectListCommand(projectList)));
    projectMenu.add(new MenuItem("조회", new ProjectViewCommand(projectList)));
    projectMenu.add(new MenuItem("변경", new ProjectUpdateCommand(projectList, memberHandler)));
    projectMenu.add(new MenuItem("삭제", new ProjectDeleteCommand(projectList)));
    mainMenu.add(projectMenu);

    MenuGroup boardMenu = new MenuGroup("게시판");
    boardMenu.add(new MenuItem("등록", new BoardAddCommand(boardList)));
    boardMenu.add(new MenuItem("목록", new BoardListCommand(boardList)));
    boardMenu.add(new MenuItem("조회", new BoardViewCommand(boardList)));
    boardMenu.add(new MenuItem("변경", new BoardUpdateCommand(boardList)));
    boardMenu.add(new MenuItem("삭제", new BoardDeleteCommand(boardList)));
    mainMenu.add(boardMenu);

    mainMenu.add(new MenuItem("도움말", new HelpCommand()));
    mainMenu.add(new MenuItem("명령내역", new HistoryCommand()));

    mainMenu.setExitMenuTitle("종료");
  }


  public static void main(String[] args) {
    new App().execute();
  }

  void execute() {
    String appTitle = "[프로젝트 관리 시스템]";
    String line = "----------------------------------";

    try {
      mainMenu.execute();

    } catch (Exception ex) {
      System.out.println("실행 오류!");
      ex.printStackTrace();

    } finally {
      saveData();
    }

    System.out.println("종료합니다.");

    Prompt.close();
  }

  private void loadData() {
//    loadJson(userList, "user.json", User.class);
//    loadJson(projectList, "project.json", Project.class);
//    loadJson(boardList, "board.json", Board.class);

    try (FileInputStream in = new FileInputStream("data.xlsx")) {
      XSSFWorkbook workbook = new XSSFWorkbook(in);

      loadUsers(userList, workbook);
      loadBoards(boardList, workbook);
      loadProjects(projectList, workbook);


    } catch (Exception e) {
      System.out.println("데이터 로드 중 오류 발생!");
      e.printStackTrace();
    }

    System.out.println("데이터를 로드했습니다.");
  }

  private void loadUsers(List<User> userList, XSSFWorkbook workbook) throws Exception {
    XSSFSheet sheet = workbook.getSheet("users");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      User user = new User();
      Row row = sheet.getRow(i);

      user.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
      user.setName(row.getCell(1).getStringCellValue());
      user.setEmail(row.getCell(2).getStringCellValue());
      user.setPassword(row.getCell(3).getStringCellValue());
      user.setTel(row.getCell(4).getStringCellValue());

      userList.add(user);
    }
    initSeqNo(userList, User.class);
  }

  private void loadBoards(List<Board> boardList, XSSFWorkbook workbook) throws Exception {
    XSSFSheet sheet = workbook.getSheet("boards");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Board board = new Board();
      Row row = sheet.getRow(i);

      board.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
      board.setTitle(row.getCell(1).getStringCellValue());
      board.setContent(row.getCell(2).getStringCellValue());

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      board.setCreatedDate(formatter.parse(row.getCell(3).getStringCellValue()));
      board.setViewCount((int) row.getCell(4).getNumericCellValue());

      boardList.add(board);
    }
    initSeqNo(boardList, Board.class);
  }

  private void loadProjects(List<Project> projectList, XSSFWorkbook workbook) throws Exception {
    XSSFSheet sheet = workbook.getSheet("projects");

    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Project project = new Project();
      Row row = sheet.getRow(i);

      project.setNo(Integer.parseInt(row.getCell(0).getStringCellValue()));
      project.setTitle(row.getCell(1).getStringCellValue());
      project.setDescription(row.getCell(2).getStringCellValue());
      project.setStartDate(row.getCell(3).getStringCellValue());
      project.setEndDate(row.getCell(4).getStringCellValue());

      for (String member : row.getCell(5).getStringCellValue().split(",")) {
        for (User user : userList) {
          if (user.getNo() == Integer.parseInt(member)) {
            project.getMembers().add(user);
          }
        }
      }
      projectList.add(project);
    }

    initSeqNo(projectList, Project.class);
  }

  private <E> void initSeqNo(List<E> list, Class<E> elementType) throws Exception {
    int maxSeqNo = 0;
    for (Object element : list) {
      SequenceNo seqObj = (SequenceNo) element;
      if (seqObj.getNo() > maxSeqNo) {
        maxSeqNo = seqObj.getNo();
      }
    }
    Method method = elementType.getMethod("initSeqNo", int.class);
    method.invoke(null, maxSeqNo);
    // 위 코드는 다음과 같다.
    // 예) User.initSeqNo(maxSeqNo);
  }

  private <E> void loadJson(List<E> list, String filename, Class<E> elementType) {
    try (BufferedReader in = new BufferedReader(new FileReader(filename))) {

      StringBuilder strBuilder = new StringBuilder();
      String line;
      while ((line = in.readLine()) != null) {
        strBuilder.append(line);
      }

      list.addAll((List<E>) new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .create().fromJson(strBuilder.toString(),
              TypeToken.getParameterized(List.class, elementType)));

      // 읽어 들인 객체의 타입이 SequenceNo 구현체라면
      // 일련 번호를 객체 식별 번호로 사용한다는 것이기 때문에
      // 목록에 저장된 객체 중에서 일련 번호가 가장 큰 일련 번호를 알아내서
      // 클래스의 스태틱 필드에 설정해야 한다.
      for (Class<?> type : elementType.getInterfaces()) {
        if (type == SequenceNo.class) {
          initSeqNo(list, elementType);
          break;
        }
      }


    } catch (Exception e) {
      System.out.println(filename + " 파일 로딩 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveData() {
    try {
      XSSFWorkbook workbook = new XSSFWorkbook();
      saveUsers(workbook);
      saveBoards(workbook);
      saveProjects(workbook);

      try (FileOutputStream out = new FileOutputStream("data.xlsx")) {
        workbook.write(out);
      }
      System.out.println("데이터를 저장 했습니다.");
    } catch (Exception e) {
      System.out.println("데이터 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void saveUsers(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("users");

    String[] cellHeaders = {"no", "name", "email", "password", "tel"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    for (int i = 1; i <= userList.size(); i++) {
      User user = userList.get(i - 1);
      Row dataRow = sheet.createRow(i);

      dataRow.createCell(0).setCellValue(String.valueOf(user.getNo()));
      dataRow.createCell(1).setCellValue(user.getName());
      dataRow.createCell(2).setCellValue(user.getEmail());
      dataRow.createCell(3).setCellValue(user.getPassword());
      dataRow.createCell(4).setCellValue(user.getTel());
    }
  }

  private void saveBoards(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("boards");

    String[] cellHeaders = {"no", "title", "content", "create_date", "view_count"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    for (int i = 1; i <= boardList.size(); i++) {
      Board board = boardList.get(i - 1);
      Row dataRow = sheet.createRow(i);

      dataRow.createCell(0).setCellValue(String.valueOf(board.getNo()));
      dataRow.createCell(1).setCellValue(board.getTitle());
      dataRow.createCell(2).setCellValue(board.getContent());

      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      dataRow.createCell(3).setCellValue(formatter.format(board.getCreatedDate()));
      dataRow.createCell(4).setCellValue(board.getViewCount());
    }
  }

  private void saveProjects(XSSFWorkbook workbook) {
    XSSFSheet sheet = workbook.createSheet("projects");

    String[] cellHeaders = {"no", "title", "description", "start_date", "end_date", "members"};
    Row headerRow = sheet.createRow(0);
    for (int i = 0; i < cellHeaders.length; i++) {
      headerRow.createCell(i).setCellValue(cellHeaders[i]);
    }

    for (int i = 1; i <= projectList.size(); i++) {
      Project project = projectList.get(i - 1);
      Row dataRow = sheet.createRow(i);

      dataRow.createCell(0).setCellValue(String.valueOf(project.getNo()));
      dataRow.createCell(1).setCellValue(project.getTitle());
      dataRow.createCell(2).setCellValue(project.getDescription());
      dataRow.createCell(3).setCellValue(project.getStartDate());
      dataRow.createCell(4).setCellValue(project.getEndDate());

      StringBuilder strBuilder = new StringBuilder();
      for (User member : project.getMembers()) {
        if (strBuilder.length() > 0) {
          strBuilder.append(",");
        }
        strBuilder.append(member.getNo());
      }
      dataRow.createCell(5).setCellValue(strBuilder.toString());
    }
  }

  private void saveJson(Object obj, String filename) {
    try (FileWriter out = new FileWriter(filename)) {
      out.write(new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss")
          .create()
          .toJson(obj));
    } catch (IOException e) {
      System.out.println(filename + " 파일 저장 중 오류 발생!");
      e.printStackTrace();
    }
  }
}
