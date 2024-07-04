package study.patterns.ex01.step6;

public class Sedan extends Car {
  boolean sunroof;
  boolean auto;

  @Override
  public String toString() {
    return "Sedan [sunroof=" + sunroof + ", auto=" + auto + ", maker=" + maker + ", model=" + model
        + ", cc=" + cc + "]";
  }

  protected Sedan() {}

  @Override
  public void run() {
    System.out.printf("%s %s하고 달린다!!\n", this.model, this.sunroof ? "선루프 열고" :"선루프 닫고");

  }

  @Override
  public void start() {
    System.out.printf("%s 시동 건다!!\n", this.model);
  }

  @Override
  public void stop() {
    System.out.printf("%s 멈춘다!!\n", this.model);
  }

}
