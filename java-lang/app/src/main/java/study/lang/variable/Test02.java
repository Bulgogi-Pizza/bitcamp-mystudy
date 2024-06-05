package study.lang.variable;

public class Test02 {
  public static void main(String[] args) {
    // 실습
    char c1 = 44032; // '가' 정수
    char c2 = '\uac00'; // '가'u\0000
    char c3 = '가'; // '가'쉽게

    int i = c1;

    System.out.println(i);

    System.out.println(c1);
    System.out.println(c2);
    System.out.println(c3);
  }
}
