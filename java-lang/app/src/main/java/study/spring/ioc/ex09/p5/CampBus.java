package study.spring.ioc.ex09.p5;

import org.springframework.stereotype.Component;
import study.spring.ioc.ex09.Car;
import study.spring.ioc.ex09.Engine;

@Component
public class CampBus extends Car {

  public CampBus(Engine engine) {
    super(engine);
  }
}
