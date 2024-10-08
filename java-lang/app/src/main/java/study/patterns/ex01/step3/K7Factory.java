package study.patterns.ex01.step3;

import study.uml.class_diagram.Sedan;

public class K7Factory extends CarFactory{
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
