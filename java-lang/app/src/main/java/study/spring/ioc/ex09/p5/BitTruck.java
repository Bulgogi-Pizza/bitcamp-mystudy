package study.spring.ioc.ex09.p5;

import org.springframework.stereotype.Component;
import study.spring.ioc.ex09.Car;
import study.spring.ioc.ex09.Engine;

@Component
public class BitTruck extends Car {

  public BitTruck(Engine engine) {
    super(engine);
  }
}
