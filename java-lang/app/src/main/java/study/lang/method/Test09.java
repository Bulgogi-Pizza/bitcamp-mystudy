package study.lang.method;

public class Test09 {
  public static void main(String[] args) {
    int[] values = new int[] {100, 110, 120};
    m1(values);
    System.out.println(values[0] + values[1]);
    String str = "hihi";
    str = "Holy";
    int i = 100;
    i = 300;

    System.out.println(str);
    m2(str);
    System.out.println(str);

    String[] strArray = new String[] {"hihi","holy","ssss"};
    System.out.println(strArray[0]);
    m3(strArray);
    System.out.println(strArray[0]);
  }
  static void m1(int[] values) {
    values[0] = 200;
  }

  static void m2(String str) {
    System.out.println(str);
    str = "Holy";
    System.out.println(str);
  }
  static void m3(String[] strArray) {
    System.out.println(strArray[0]);
    strArray[0] = "HIHI";
    System.out.println(strArray[0]);
  }
}
