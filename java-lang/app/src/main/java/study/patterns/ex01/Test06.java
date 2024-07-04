// 1) 패턴 적용 전
// 2) private 생성자 + Factory Method 설계 패턴
// 3) Abstract Factory 설계 패턴
// 4) Abstract Factory 설계 패턴 + 인터페이
// 5) Singleton 설계 패턴
// 6) Template Method 패턴

package study.patterns.ex01;

import study.patterns.ex01.step6.Car;
import study.patterns.ex01.step6.CarFactory;
import study.patterns.ex01.step6.K7Factory;
import study.patterns.ex01.step6.SonataFactory;

public class Test06 {
  public static void main(String[] args) {
    // Sedan sonata = new Sedan(); 불가능

    SonataFactory sonataFactory = SonataFactory.getInstance();
    SonataFactory sonataFactory2 = SonataFactory.getInstance();
    SonataFactory sonataFactory3 = SonataFactory.getInstance();
    SonataFactory sonataFactory4 = SonataFactory.getInstance();
    K7Factory k7Factory = K7Factory.getInstance();
    K7Factory k7Factory2 = K7Factory.getInstance();
    K7Factory k7Factory3 = K7Factory.getInstance();
    K7Factory k7Factory4 = K7Factory.getInstance();

    play(sonataFactory);
    System.out.println(k7Factory == k7Factory2);
    System.out.println(k7Factory3 == k7Factory2);
    System.out.println(k7Factory4 == k7Factory);
    System.out.println(k7Factory4 == k7Factory2);
    System.out.println(sonataFactory == sonataFactory2);
    System.out.println(sonataFactory3 == sonataFactory2);
    System.out.println(sonataFactory4 == sonataFactory);
    System.out.println(sonataFactory4 == sonataFactory2);
    play(k7Factory);
    play(k7Factory2);
    play(k7Factory3);
    play(k7Factory4);



  }

  static void play (CarFactory carFactory) {
    Car car = carFactory.createCar();   //CarFactory 사용 규칙에 따라 메서드 실행
    car.play();
  }
}
