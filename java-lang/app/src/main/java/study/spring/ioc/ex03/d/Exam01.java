// 생성자의 파라미터 값을 지정하는 방법 III
package study.spring.ioc.ex03.d;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex03/d/application-context.xml");

    // SpringUtils.printBeanNames(iocContainer);
  }

}


