// AOP(Aspect-Oriented Programming) - AOP 필터를 적용하는 방법
package study.spring.ioc.ex13.b;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;

public class Exam02 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex13/b/application-context-02.xml");

    SpringUtils.printBeanList(iocContainer);

    X x = iocContainer.getBean(X.class);
    x.m1();
    System.out.println("-----------------------");

    x.m2();
    System.out.println("-----------------------");

    x.m3();

  }

}


