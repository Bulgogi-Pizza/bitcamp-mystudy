package study.lang.method;

public class Test02 {
  public static void main(String[] args) {
    m1(new String("최동인"), 27);
    m1("임꺽정", 99);
    m1("유관순", 88);
  }
  static void m1(String name, int age) {
    System.out.printf("%s(%d)님 반갑습니다.\n", name, age);
  }
}
