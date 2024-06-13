package bitcamp.myapp;

import java.util.Scanner;

public class App {

    static Scanner keyboardScanner = new Scanner(System.in);

    static String[] mainMenus = new String[] {
            "회원", "팀", "프로젝트", "게시판", "도움말", "종료"
    };

    static String[][] subMenus = {
        {"등록", "목록", "조회", "변경", "삭제", "이전"},
        {"등록", "목록", "조회", "변경", "삭제", "이전"},
        {"등록", "목록", "조회", "변경", "삭제", "이전"},
        {"등록", "목록", "조회", "변경", "삭제", "이전"}
    };

    public static void main(String[] args) {
        Scanner keyboardScanner = new Scanner(System.in);

        printMainMenu();

        String command;
        while (true) {
            try {
                command = mainPrompt();

                if (command.equals("menu")) {
                    printMainMenu();
                } else {
                    int menuNo = Integer.parseInt(command);
                    String menuTitle = getMenuTitle(menuNo);

                    if (menuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else if (getMenuTitle(menuNo).equals("종료")) {
                        break;
                    } else if (isHavingMenu(menuNo)) {
                        intoLevelTwo(menuNo);
                        printMainMenu();
                    } else {
                        System.out.println(getMenuTitle(menuNo));
                    }
                }
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }

        System.out.println("종료합니다.");

        keyboardScanner.close();
    }

    static void intoLevelTwo(int menuNo){
        printSubMenu(menuNo);

        String command;
        while (true) {
            try {
                command = subPrompt(menuNo);

                if (command.equals("menu")) {
                    printSubMenu(menuNo);
                } else {
                    int subMenuNo = Integer.parseInt(command);
                    String subMenuTitle = getSubMenuTitle(menuNo, subMenuNo);

                    if (subMenuTitle == null) {
                        System.out.println("유효한 메뉴 번호가 아닙니다.");
                    } else if (getSubMenuTitle(menuNo, subMenuNo).equals("등록")) {
                        regist(menuNo);
                    } else if (getSubMenuTitle(menuNo, subMenuNo).equals("목록")) {
                        list(menuNo);
                    } else if (getSubMenuTitle(menuNo, subMenuNo).equals("조회")) {
                        check(menuNo);
                    } else if (getSubMenuTitle(menuNo, subMenuNo).equals("삭제")) {
                        delete(menuNo);
                    } else if (getSubMenuTitle(menuNo, subMenuNo).equals("이전")) {
                        break;
                    } else {
                        System.out.println(getSubMenuTitle(menuNo, subMenuNo));
                    }
                }
            }
            catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }

    static void regist(int menuNo) {
        switch (getMenuTitle(menuNo)) {
            case "회원":
                System.out.println("[등록]");
                System.out.println();
                break;
            case "팀":
                break;
            case "프로젝트":
                break;
            case "게시판":
                break;
            default:
                break;
        }
    }

    static void list(int menuNo){
        switch (getMenuTitle(menuNo)) {
            case "회원":
                break;
            case "팀":
                break;
            case "프로젝트":
                break;
            case "게시판":
                break;
            default:
                break;
        }
    }

    static void check(int menuNo){
        switch (getMenuTitle(menuNo)) {
            case "회원":
                break;
            case "팀":
                break;
            case "프로젝트":
                break;
            case "게시판":
                break;
            default:
                break;
        }
    }

    static void delete(int menuNo){
        switch (getMenuTitle(menuNo)) {
            case "회원":
                break;
            case "팀":
                break;
            case "프로젝트":
                break;
            case "게시판":
                break;
            default:
                break;
        }
    }

    static void printMainMenu() {
        String boldAnsi = "\033[1m";
        String redAnsi = "\033[31m";
        String resetAnsi = "\033[0m";

        String appTitle = "[팀 프로젝트 관리 시스템]";
        String line = "----------------------------------";

        System.out.println(boldAnsi + line + resetAnsi);
        System.out.println(boldAnsi + appTitle + resetAnsi);

        for (int i = 0; i < mainMenus.length; i++) {
            if (mainMenus[i].equals("종료")) {
                System.out.printf("%s%d. %s%s\n", (boldAnsi + redAnsi), (i + 1), mainMenus[i], resetAnsi);
            } else {
                System.out.printf("%d. %s\n", (i + 1), mainMenus[i]);
            }
        }

        System.out.println(boldAnsi + line + resetAnsi);
    }

    static void printSubMenu(int menuNo) {
        String title = getMenuTitle(menuNo);
        String[] menus = getSubMenu(menuNo);

        System.out.println("[" + title + "]");

        for (int i = 0; i < menus.length; i++) {
            if (i == menus.length - 1){
                System.out.printf("%d. %s\n", (9), menus[i]);
            }
            else {
                System.out.printf("%d. %s\n", (i + 1), menus[i]);
            }
        }
    }

    static String mainPrompt() {
        System.out.print("메인> ");

        return keyboardScanner.nextLine();
    }

    static String subPrompt(int menuNo) {
        String subMenuTitle = getMenuTitle(menuNo);

        System.out.print("메인/" + subMenuTitle + "> ");

        return keyboardScanner.nextLine();
    }

    static boolean isHavingMenu(int menuNo) {
        return menuNo >= 1 && menuNo <= 4;
    }

    static boolean isValidateMenu(int menuNo) {
        return menuNo >= 1 && menuNo <= mainMenus.length;
    }

    static boolean isValidateSubMenu(int menuNo, int subMenuNo) {
        String[] subMenu = getSubMenu(menuNo);

        return subMenuNo >= 1 && subMenuNo <= subMenu.length - 1 || subMenuNo == 9;
    }

    static String getMenuTitle(int menuNo) {
        if(!isValidateMenu(menuNo)){
            return null;
        }
        return mainMenus[menuNo-1];
    }

    static String getSubMenuTitle(int menuNo, int subMenuNo){
        String[] subMenu = getSubMenu(menuNo);

        if(!isValidateSubMenu(menuNo, subMenuNo)){
            return null;
        } else {
            if(subMenuNo <= subMenu.length - 1){
                return subMenu[subMenuNo-1];
            } else if (subMenuNo == 9) {
                return subMenu[subMenu.length-1];
            }
            else {
                return null;
            }
        }
    }

    static String[] getSubMenu(int menuNo) {
        return subMenus[menuNo+1];
    }
}
