package study.lang.string;

public class Test03 {
  public static void main(String[] args) {
    java.util.Scanner keyboard = new java.util.Scanner(System.in);

    System.out.print("입력1 : ");
    String s1 = keyboard.nextLine();    // 사용자가 입력한 문자열을 저장한 String 인스턴스를 새로 만들고 그 주소를 리턴한다.

    System.out.println("입력2 : ");    // 사용자가 입력한 문자열을 저장한 String 인스턴스를 새로 만들고 그 주소를 리턴한다.
    String s2 = keyboard.nextLine();

    System.out.println(s1 == s2);

    keyboard.close();

  }
}
