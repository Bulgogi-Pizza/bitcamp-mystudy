// 객체 꺼내기 - 객체를 꺼낼 때는 ID나 별명 모두 사용할 수 있다.
package study.spring.ioc.ex02.b;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;
import study.spring.ioc.ex02.Car;

public class Exam03 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex02/b/application-context.xml");

    // 빈의 id와 클래스명을 출력하기
    SpringUtils.printBeanList(iocContainer);

    System.out.println("-------------------------");

    Car c1 = (Car) iocContainer.getBean("c5");
    Car c2 = (Car) iocContainer.getBean("c51");

    System.out.println(c1);
    System.out.println(c2);

    System.out.println(iocContainer.getBean("c5")); // ID
    System.out.println(iocContainer.getBean("c51")); // 별명
  }

}


