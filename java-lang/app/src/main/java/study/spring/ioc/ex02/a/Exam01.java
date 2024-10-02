// 객체 생성 - <bean> 태그 사용법
package study.spring.ioc.ex02.a;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import study.spring.ioc.SpringUtils;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(
        "study/spring/ioc/ex02/a/application-context.xml");

    SpringUtils.printBeanList(iocContainer);
  }

}


