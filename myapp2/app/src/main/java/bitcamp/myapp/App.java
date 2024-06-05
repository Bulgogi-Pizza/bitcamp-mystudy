package bitcamp.myapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String selectMenu;
        boolean exitFlag;

        // 메뉴 출력
        Menu.viewMenu();

        // 메뉴 선택
        do {
            System.out.print("> ");
            selectMenu = scanner.next();
            exitFlag = Menu.selectMenu(selectMenu);
        } while(exitFlag);

        scanner.close();
    }
}
