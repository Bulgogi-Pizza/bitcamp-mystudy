package study.oop.lambda;

public class Test01 {
  interface Player {
    void play();
  }

  public static void main(String[] args) {
    // 1) 일반 클래스
    class MyPlayer implements Player {

      @Override
      public void play() {
        // TODO Auto-generated method stub

      }

    }
    Player p1 = new MyPlayer();
    p1.play();

    // 2) 익명 클래스
    Player p2 = new Player() {

    };
    p2.play();

    // 3) 익명 클래스 2
    new Player() {
      @Override
      public void play() {
        System.out.println("익명 클래스 2");
      }
    }.play();


    // 4) 람다
    Player p4 = () -> System.out.println("익명 클래스 람다!");

    // 5) 메소드 변환식
    Player p5 = new MyPlayer()::play;

    Player p6 = () -> System.out.println("hi");
    p6.play();

    Player p7 = new Player() {
      @Override
      public void play() {
        System.out.println("gg");
      }
    };
    p7.play();


  }
}
