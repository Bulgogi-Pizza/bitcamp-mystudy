package bitcamp.myapp;

import java.util.Scanner;
public class App {
    public static void main(String[] args) {
        String appTitle = "팀 프로젝트 관리 시스템";
        String line = "---------------------------";
        String[] menu = {"회원", "팀", "프로젝트", "게시판", "도움말", "종료"};

        System.out.println("\u001B[1m" + line);
        System.out.println("[" + appTitle + "]\u001B[0m\n");
        for (int i = 0; i < menu.length; i++) {
            if (i == 5){
                System.out.println((i+1) + ". \u001B[31m" + menu[i] + "\u001B[0m");
            } else {
                System.out.println((i + 1) + ". " + menu[i]);
            }
        }
        System.out.println("\u001B[1m" + line + "\u001B[0m");

        Scanner a = new Scanner(System.in);
        int b = a.nextInt();
        System.out.println(b);


    }
}
