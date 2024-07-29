package bitcamp.myapp.dao.stub;

import static bitcamp.net.ResponseStatus.SUCCESS;

import bitcamp.myapp.dao.ProjectDao;
import bitcamp.myapp.vo.Project;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class ProjectDaoStub implements ProjectDao {

  private ObjectInputStream in;
  private ObjectOutputStream out;
  private String dataName;

  public ProjectDaoStub(ObjectInputStream in, ObjectOutputStream out, String dataName)
      throws IOException {
    this.in = in;
    this.out = out;
    this.dataName = dataName;
  }

  @Override
  public boolean insert(Project project) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("insert");
    out.writeObject(project);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public List<Project> list() throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("list");
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (List<Project>) in.readObject();
    } else {
      return null;
    }

  }

  @Override
  public Project findBy(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("get");
    out.writeInt(no);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return (Project) in.readObject();
    } else {
      return null;
    }
  }

  @Override
  public boolean update(Project project) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("update");
    out.writeObject(project);
    out.flush();

    if (in.readUTF().equals(SUCCESS)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public boolean delete(int no) throws Exception {
    out.writeUTF(dataName);
    out.writeUTF("delete");
    out.writeInt(no);
    out.flush();

    return in.readUTF().equals(SUCCESS);
  }
}
