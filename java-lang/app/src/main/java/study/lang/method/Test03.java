package study.lang.method;

public class Test03 {
  public static void main(String[] args) {
    String message = m1();
    String message2 = new String(m1());
    message2 = message;
    String message3 = m1();
    System.out.println(message == message2);
    System.out.println(message == message3);
    System.out.println(message2 == message3);
    System.out.println(new String(m1()));
    System.out.println(message);
  }
  static String m1() {
    return "개구려";
  }
}
