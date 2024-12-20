// AOP(Aspect-Oriented Programming) - AOP 필터 적용 전
package study.spring.ioc.ex13.a.step1;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex13/a/step1/application-context.xml");

    SpringUtils.printBeanList(iocContainer);

    X x = iocContainer.getBean(X.class);
    x.m1();
  }

}


