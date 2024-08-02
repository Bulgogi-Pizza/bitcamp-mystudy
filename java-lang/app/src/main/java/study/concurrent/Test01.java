package study.concurrent;

public class Test01 {
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      System.out.printf("==> %d\n", i);
    }
    System.out.println("----------------");

    PrintThread t1 = new PrintThread();
    PrintThread t2 = new PrintThread();

    t1.start();
    t2.start();
  }

}

class PrintThread extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 2000; i++) {
      System.out.printf("%s-%d~~~~~~~~~~~~~>%d \n",this.getName(), this.getId(), i);
      double k = 20.2 * 2.2 / 6.2;
    }
  }
}
