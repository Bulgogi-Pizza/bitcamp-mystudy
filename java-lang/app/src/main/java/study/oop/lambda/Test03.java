package study.oop.lambda;

public class Test03 {
  interface Player {
    void play();
  }

  public static void main(String[] args) {
    // 1
    class MyPlayer implements Player {
      @Override
      public void play() {
        System.out.println("1111");
      }
    }
    test(new MyPlayer());

    // 2
    Player p1 = new Player() {
      @Override
      public void play() {
        System.out.println("2222");
      }
    };
    test(p1);

    // 3
    test(new Player() {
      @Override
      public void play() {
        System.out.println("3333");
      }
    });

    // 4
    test(() -> {
      System.out.println("4444");
    });

    // 5
    Player p5 = () -> System.out.println("5555");
    test(p5);

    // 6
    test(() -> System.out.println("6666"));


  }

  static void test(Player player) {
    player.play();
  }
}
