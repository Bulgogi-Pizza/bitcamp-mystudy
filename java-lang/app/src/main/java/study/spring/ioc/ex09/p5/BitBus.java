package study.spring.ioc.ex09.p5;

import org.springframework.stereotype.Component;
import study.spring.ioc.ex09.Car;
import study.spring.ioc.ex09.Engine;

@Component
public class BitBus extends Car {
  public BitBus(Engine engine) {
    super(engine);
  }
}
