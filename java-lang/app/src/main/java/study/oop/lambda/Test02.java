package study.oop.lambda;

public class Test02 {
  interface Command {
    void execute();
  }

  public static void main(String[] args) {
    Command c1 = new Command() {
      @Override
      public void execute() {
        System.out.println("hhh");
      }
    };

    c1.execute();


  }
}
