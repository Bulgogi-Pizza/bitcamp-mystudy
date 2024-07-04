package study.patterns.ex01.step2;

public class Sedan extends Car {
  boolean sunroof;
  boolean auto;

  @Override
  public String toString() {
    return "Sedan [sunroof=" + sunroof + ", auto=" + auto + ", maker=" + maker + ", model=" + model
        + ", cc=" + cc + "]";
  }

  private Sedan() {}


  // Factory Method
  public static Sedan create(String model) {
    Sedan s = new Sedan();

    switch(model) {
      case "Sonata":
        s.maker = "Hyundai";
        s.model = "Sonata";
        s.cc = 1998;
        s.auto = true;
        s.sunroof = false;
        break;
      case "K7":
        s.maker = "Kia";
        s.model = "K7";
        s.cc = 2500;
        s.auto = true;
        s.sunroof = true;
        break;

    }
    return s;
  }

}
