// AOP(Aspect-Oriented Programming) - 애노테이션으로 AOP 설정하기
package study.spring.ioc.ex13.f;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ClassPathXmlApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex13/f/application-context.xml");

    SpringUtils.printBeanList(iocContainer);

    try {
      Caller caller = iocContainer.getBean(Caller.class);
      caller.test();
    } catch (Exception e) {
      System.err.println("오류 발생!");
    }
  }

}


