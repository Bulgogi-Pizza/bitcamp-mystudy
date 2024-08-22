package bitcamp.myapp.dao;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;

public class DaoFactory {

  private SqlSession sqlSession;

  // 생성자: DaoFactory 객체가 생성될 때 SqlSession 객체를 받아서 초기화함
  public DaoFactory(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  // DAO 인터페이스 타입의 프록시 객체를 생성하여 반환
  public <T> T createObject(Class<T> daoType) throws Exception {
    return (T) Proxy.newProxyInstance(
        this.getClass().getClassLoader(), // 클래스 로더 지정
        new Class[]{daoType},             // 프록시가 구현할 인터페이스
        this::invoke);                    // 메서드 호출 시 실행할 핸들러 지정
  }

  // 프록시 객체의 메서드가 호출될 때 실행되는 메서드
  public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
    // 메서드가 속한 DAO 인터페이스의 이름을 가져옴 (네임스페이스로 사용)
    String namespace = proxy.getClass().getInterfaces()[0].getSimpleName();
    // 호출된 메서드의 이름을 가져옴 (SQL ID로 사용)
    String sqlId = method.getName();
    // 네임스페이스와 SQL ID를 조합하여 MyBatis의 statement ID 생성
    String statement = String.format("%s.%s", namespace, sqlId);

    Object paramValue = null;
    if (args != null) {
      if (args.length == 1) {
        // 메서드에 전달된 인자가 하나일 경우 그대로 사용
        paramValue = args[0];
      } else {
        // 메서드에 전달된 인자가 여러 개일 경우, 매개변수 이름을 키로 하여 맵에 저장
        Parameter[] params = method.getParameters();
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
          Param anno = params[i].getAnnotation(Param.class); // 매개변수에 @Param 애노테이션이 있는지 확인
          map.put(anno.value(), args[i]);                    // 애노테이션의 값을 키로 하여 맵에 저장
        }
        paramValue = map;
      }
    }

    // 메서드의 반환 타입에 따라 MyBatis의 적절한 메서드를 호출
    Class<?> returnType = method.getReturnType();

    if (returnType == List.class) {
      // 반환 타입이 List일 경우 selectList 호출
      return sqlSession.selectList(statement, paramValue);
    } else if (returnType == int.class || returnType == void.class || returnType == boolean.class) {
      // 반환 타입이 int, void, boolean일 경우 insert 호출
      int count = sqlSession.insert(statement, paramValue);
      if (returnType == boolean.class) {
        // 반환 타입이 boolean이면, 성공 여부를 반환
        return count > 0;
      } else if (returnType == void.class) {
        // 반환 타입이 void이면, null을 반환
        return null;
      } else {
        // 반환 타입이 int이면, 영향받은 레코드 수를 반환
        return count;
      }
    } else {
      // 그 외의 경우 selectOne 호출
      return sqlSession.selectOne(statement, paramValue);
    }
  }
}