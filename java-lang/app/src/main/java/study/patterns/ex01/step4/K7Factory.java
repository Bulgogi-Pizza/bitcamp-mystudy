package study.patterns.ex01.step4;

public class K7Factory implements CarFactory{
  @Override
  public Sedan createCar() {
    Sedan s = new Sedan();

    s.maker = "Kia";
    s.model = "K7";
    s.cc = 2500;
    s.auto = true;
    s.sunroof = true;

    return s;
  }
}
