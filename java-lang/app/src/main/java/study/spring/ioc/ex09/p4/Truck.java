package study.spring.ioc.ex09.p4;

import org.springframework.stereotype.Component;
import study.spring.ioc.ex09.Car;
import study.spring.ioc.ex09.Engine;

@Component
public class Truck extends Car {

  public Truck(Engine engine) {
    super(engine);
  }
}
