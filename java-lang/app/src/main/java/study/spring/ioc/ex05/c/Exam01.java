// 컬렉션 타입의 프로퍼티 값 설정 - Map<,>
package study.spring.ioc.ex05.c;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam01 {

  public static void main(String[] args) {
    ApplicationContext iocContainer = new ClassPathXmlApplicationContext(//
        "study/spring/ioc/ex05/c/application-context.xml");

    System.out.println(iocContainer.getBean("c1"));
  }

}


