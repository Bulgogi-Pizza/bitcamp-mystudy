package bitcamp.myapp.dao;

import bitcamp.myapp.vo.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

  boolean insert(User user) throws Exception;

  List<User> list() throws Exception;

  User findBy(int no) throws Exception;

  User findByEmailAndPassword(@Param("email") String email, @Param("password") String password)
      throws Exception;

  User findByEmail(String email) throws Exception;

  boolean update(User user) throws Exception;

  boolean delete(int no) throws Exception;

}
