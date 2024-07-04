package study.patterns.ex01.step3;

public class SonataFactory extends CarFactory {
  @Override
  public Sedan createCar() {
    Sedan s = new Sedan();

    s.maker = "Hyundai";
    s.model = "Sonata";
    s.cc = 1998;
    s.auto = true;
    s.sunroof = false;

    return s;
  }

}
