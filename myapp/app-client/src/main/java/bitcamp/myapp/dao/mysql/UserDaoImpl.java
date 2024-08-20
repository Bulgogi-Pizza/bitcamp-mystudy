package bitcamp.myapp.dao.mysql;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {

    private SqlSession sqlSession;

    public UserDaoImpl(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public boolean insert(User user) throws Exception {
        return sqlSession.insert("userDao.insert", user) > 0;
    }

    @Override
    public List<User> list() throws Exception {
        return sqlSession.selectList("userDao.list");
    }

    @Override
    public User findBy(int no) throws Exception {
        return sqlSession.selectOne("userDao.findBy", no);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) throws Exception {
        Map<String, String> emailPwdMap = new HashMap<>();
        emailPwdMap.put("email", email);
        emailPwdMap.put("pwd", password);

        return sqlSession.selectOne("userDao.findByEmailAndPassword", emailPwdMap);
    }

    @Override
    public boolean update(User user) throws Exception {
        int count = sqlSession.update("userDao.update", user);
        return count > 0;
    }

    @Override
    public boolean delete(int no) throws Exception {
        int count = sqlSession.delete("userDao.delete", no);
        return count > 0;
    }
}
