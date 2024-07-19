package study.oop.lambda;

public class Test06 {

  static class MyCalculator {
    public static int plus(int a, int b) {return a + b;}
    public static int minus(int a, int b) {return a - b;}
    public static int multiple(int a, int b) {return a * b;}
    public static int divide(int a, int b) {return a / b;}
  }

  interface Calculator {
    int compute(int x, int y);
  }

  public static void main(String[] args) {

    class $1 implements Calculator {
      @Override
      public int compute(int x, int y) {
        return MyCalculator.plus(x, y);
      }
    }
    Calculator obj0 = new $1();

    Calculator calculator = MyCalculator::plus;


    int result = calculator.compute(100, 200);
    System.out.println(result);
  }
}
