package bitcamp.myapp;

import bitcamp.context.ApplicationContext;
import bitcamp.listener.ApplicationListener;
import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.listener.InitApplicationListener;
import bitcamp.myapp.vo.User;
import bitcamp.net.Prompt;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerApp {

  private static final String GOODBYE = "<[goodbye!]>";

  List<ApplicationListener> listeners = new ArrayList<>();
  ApplicationContext appCtx = new ApplicationContext();

  public static void main(String[] args) {
    try {
      ServerApp app = new ServerApp();
      app.addApplicationListener(new InitApplicationListener());
      app.execute();
    } catch (Exception e) {
      System.out.println("서버 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }

  private void addApplicationListener(ApplicationListener listener) {
    listeners.add(listener);
  }

  private void removeApplicationListener(ApplicationListener listener) {
    listeners.remove(listener);
  }

  void execute() throws Exception {
    for (ApplicationListener listener : listeners) {
      try {
        if (!listener.onStart(appCtx)) {
          System.out.println("종료합니다.");
          return;
        }
      } catch (Exception e) {
        System.out.println("리스너 실행 중 오류 발생!");
        e.printStackTrace();
      }
    }

    ServerSocket serverSocket = new ServerSocket(8888);
    System.out.println("서버 실행 중...");

    while (true) {
      service(serverSocket.accept());
    }
  }

  private void service(Socket socket) {
    new Thread(() -> {
      try {
        Prompt prompt = new Prompt(socket, appCtx);

        prompt.println("[프로젝트 관리 시스템]");
        String email = prompt.input("이메일?");
        String password = prompt.input("비밀번호?");

        UserDao userDao = (UserDao) appCtx.getAttribute("userDao");
        User loginUser = userDao.findByEmailAndPassword(email, password);
        if (loginUser == null) {
          prompt.println("이메일 또는 암호가 맞지 않습니다!");
          prompt.print(GOODBYE);
          prompt.end();
          prompt.close();
          return;
        }

        prompt.setAttribute("loginUser", loginUser);

        appCtx.getMainMenu().execute(prompt);
        prompt.print(GOODBYE);
        prompt.end();
        prompt.close();
      } catch (Exception ex) {
        System.out.println("실행 오류!");
        ex.printStackTrace();
      }
    }).start();
  }
}