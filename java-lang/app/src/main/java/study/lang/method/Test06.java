package study.lang.method;

public class Test06 {
  public static void main(String[] args) {
    int sum = 0;
    for (int i = 1; i <= 10; i++) {
      sum += i;
    }
    System.out.println(sum);
    System.out.println(sum(10));
  }
  static int sum(int n) {
    if (n != 0){
      return n + sum(n-1);
    } else {
      return 0;
    }
  }
}
