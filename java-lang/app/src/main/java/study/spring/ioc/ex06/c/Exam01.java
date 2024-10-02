// 팩토리 메서드 호출 - 인스턴스 메서드 호출
package study.spring.ioc.ex06.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex06/c/application-context.xml");

    System.out.println(iocContainer.getBean("c1"));
  }

}


