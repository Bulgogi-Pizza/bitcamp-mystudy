// 객체 생성 - <bean> 태그 사용법
package study.spring.ioc.ex02.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;
import study.spring.ioc.ex02.Car;

public class Exam02 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "study/spring/ioc/ex02/a/application-context.xml");

    SpringUtils.printBeanList(iocContainer);

    Car obj1 = (Car) iocContainer.getBean("c1");
    Car obj2 = (Car) iocContainer.getBean("c2");

    System.out.println(obj1 == obj2);

    Car obj3 = (Car) iocContainer.getBean("c2");
    System.out.println(obj2 == obj3);

    //    Car obj3 = iocContainer.getBean(Car.class);  // 같은 타입의 객체가 여러 개일 경우 예외 발생!
    //    System.out.println(obj1 == obj3);
  }

}


