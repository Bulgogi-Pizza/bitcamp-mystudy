package study.uml.class_diagram;

public class Car {

  Engine engine;
  Insuarance insuarance;
  Navigation navigation;

  public Car(Engine engine) {
    this.engine = engine;
  }

  public Insuarance getInsuarance() {
    return insuarance;
  }

  public void setInsuarance(Insuarance insuarance) {
    this.insuarance = insuarance;
  }

  public Navigation getNavigation() {
    return navigation;
  }

  public void setNavigation(Navigation navigation) {
    this.navigation = navigation;
  }

  public Engine getEngine() {
    return engine;
  }

  public void fuelUp(GasStation gasStation) {
    gasStation.inject();
  }


}
