package study.lang.method;

public class Test01 {
  public static void main(String[] args) {
    m1(); //method call (메서드 호출)
    m1();
    m1();
  }

  // method define (메서드 정의)
  static void m1() {
    System.out.println("Hello!");
  }
}
