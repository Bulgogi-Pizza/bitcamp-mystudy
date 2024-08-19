package bitcamp.bitbatis;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SqlSession {

  Connection con;

  public SqlSession(Connection con) {
    this.con = con;
  }

  public int insert(String sql, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      return stmt.executeUpdate();
    }
  }

  public int update(String sql, Object... values) throws Exception {
    return insert(sql, values);
  }

  public int delete(String sql, Object... values) throws Exception {
    return insert(sql, values);
  }

  public <T> List<T> selectList(String sql, Class<T> type, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      try (ResultSet rs = stmt.executeQuery()) {

        List<T> list = new ArrayList<>();
        while (rs.next()) {
          T obj = createObject(type);
          for (String columnName : getColumnNames(rs)) {
            setColumnValue(obj, rs, columnName);
          }
          list.add(obj);
        }
        return list;
      }
    }
  }

  public <T> T selectOne(String sql, Class<T> type, Object... values) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(sql)) {

      int inparameterIndex = 1;
      for (Object value : values) {
        stmt.setString(inparameterIndex++, value.toString());
      }

      try (ResultSet rs = stmt.executeQuery()) {

        Map<String, Method> setterMap = getSetterMap(type, rs);

        if (rs.next()) {
          T obj = createObject(type);
          for (String columnName : setterMap.keySet()) {
            Method setter = setterMap.get(columnName);
            callSetter(obj, setter, rs, columnName);
          }
          return obj;
        }
        return null;
      }
    }
  }

  private <T> Map<String, Method> getSetterMap(Class<T> type, ResultSet rs) throws Exception {
    // select 결과의 컬럼 정보 알아내기
    ResultSetMetaData metaData = rs.getMetaData();

    List<String> columnNames = new ArrayList<>();
    Map<String, Method> setterMap = new HashMap<>();

    for (int i = 1; i <= metaData.getColumnCount(); i++) {
      String columnName = metaData.getColumnLabel(i);
      setterMap.put(columnName, findSetter(type, columnName));
    }

    return setterMap;
  }

  private List<String> getColumnNames(ResultSet rs) throws Exception {
    ResultSetMetaData metaData = rs.getMetaData();
    List<String> names = new ArrayList<>();
    for (int i = 1; i <= metaData.getColumnCount(); i++) {
      names.add(metaData.getColumnLabel(i));
    }
    return names;
  }

  private <T> Method findSetter(Class<T> type, String columnName) {
    String[] names = columnName.split("_");
    Method setter = findMethod(type, toSetterName(names[0]));

    if (names.length == 1) {
      return setter;
    } else {
      // 세터의 파라미터 타입을 알아낸다
      Class paramType = setter.getParameterTypes()[0];
      return findMethod(paramType, toSetterName(names[0]));
    }
  }

  private String toSetterName(String name) {
    return "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
  }

  private <T> Method findMethod(Class<T> type, String name) {
    Method[] methods = type.getMethods();
    for (Method method : methods) {
      if (method.getName().equals(name)) {
        return method;
      }
    }
    return null;
  }

  private <T> T createObject(Class<T> type) throws Exception {
    // 레코드 값을 담을 자바 객체를 준비한다.
    Constructor<T> constructor = type.getConstructor();
    T obj = constructor.newInstance();

    return obj;
  }

  private void setColumnValue(Object obj, ResultSet rs, String columnName)
      throws Exception {

    String[] names = columnName.split("_");

    if (names.length == 1) {
      String setterName = toSetterName(names[0]);
      callSetter(obj, setterName, getColumnValue());
    }

    Type parameterType = setter.getParameterTypes()[0];

  }

  private <T> Object getColumnValue(ResultSet rs, String columnName, Class<T> type)
      throws Exception {
    if (type == int.class) {
      return rs.getInt(columnName);
    } else if (type == String.class) {
      return rs.getString(columnName);
    } else if (type == Date.class || type == java.sql.Date.class) {
      return rs.getDate(columnName);
    } else {
      return null;
    }
  }

  private void callSetter(Object obj, Method setter, ResultSet rs) {
    if (setter == null) {
      return;
    }
    Type parameterType = setter.getParameterTypes()[0];

  }

}
