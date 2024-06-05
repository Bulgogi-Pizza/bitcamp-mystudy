package bitcamp.myapp;

public class Menu {
    static String line = "---------------------------";
    static String projectTitle = "[팀 프로젝트 관리 시스템]";
    static String[] menu = {"회원", "팀", "프로젝트", "게시판", "도움말", (Ansi.redAnsi + "종료" + Ansi.resetAnsi)};

    static void viewMenu(){
        System.out.println(Ansi.boldAnsi + line + Ansi.resetAnsi);
        System.out.println(Ansi.boldAnsi + projectTitle + Ansi.resetAnsi + "\n");

        for (int i = 0; i < menu.length; i++) {
            System.out.println((i+1) + ". " + menu[i]);
        }

        System.out.println(Ansi.boldAnsi + line + Ansi.resetAnsi);
    }

    static boolean selectMenu(String selectMenu){
        boolean exitFlag = true;

        switch (selectMenu) {
            case "6":
                exitFlag = false;
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
                System.out.println(Menu.menu[Integer.parseInt(selectMenu) - 1] + " 항목입니다.");
                break;
            case "menu":
                viewMenu();
                break;
            default:
                System.out.println("잘못된 항목입니다.");
                break;
        }
        return exitFlag;
    }
}
