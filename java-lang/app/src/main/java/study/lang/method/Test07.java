package study.lang.method;

public class Test07 {
  public static void main(String[] args) {
    System.out.println(sum(100000));
  }

  static int sum(int n) {
    System.out.println(n);
    if (n != 0){
      return n + sum(n-1);
    } else {
      return 0;
    }
  }
}
