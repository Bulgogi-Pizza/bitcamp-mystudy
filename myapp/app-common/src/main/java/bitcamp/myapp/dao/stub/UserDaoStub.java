package bitcamp.myapp.dao.stub;

import static bitcamp.net.ResponseStatus.SUCCESS;

import bitcamp.myapp.dao.UserDao;
import bitcamp.myapp.vo.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class UserDaoStub implements UserDao {

  private String host;
  private int port;
  private ObjectInputStream in;
  private ObjectOutputStream out;
  private String dataName;

  public UserDaoStub(String host, int port, String dataName)
      throws IOException {
    this.host = host;
    this.port = port;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(User user) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF(dataName);
      out.writeUTF("insert");
      out.writeObject(user);
      out.flush();

      if (in.readUTF().equals(SUCCESS)) {
        return true;
      }
      return false;
    }
  }

  @Override
  public List<User> list() throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF(dataName);
      out.writeUTF("list");
      out.flush();

      if (in.readUTF().equals(SUCCESS)) {
        return (List<User>) in.readObject();
      }
      return null;
    }


  }

  @Override
  public User findBy(int no) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF(dataName);
      out.writeUTF("get");
      out.writeInt(no);
      out.flush();

      if (in.readUTF().equals(SUCCESS)) {
        return (User) in.readObject();
      }
      return null;
    }

  }

  @Override
  public boolean update(User user) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF(dataName);
      out.writeUTF("update");
      out.writeObject(user);
      out.flush();

      if (in.readUTF().equals(SUCCESS)) {
        return true;
      }
      return false;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF(dataName);
      out.writeUTF("delete");
      out.writeInt(no);
      out.flush();

      return in.readUTF().equals(SUCCESS);
    }
  }
}
