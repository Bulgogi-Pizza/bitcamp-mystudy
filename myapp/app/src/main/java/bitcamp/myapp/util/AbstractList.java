package bitcamp.myapp.util;

public abstract class AbstractList implements List {

  protected int size = 0;

  @Override
  public int size() {
    return this.size;
  }

  public boolean contains(Object obj) {
    return indexOf(obj) != -1;
  }

  @Override
  public Iterator iterator() {
    return new ListIterator(this);
  }

  static public class ListIterator implements Iterator {

    private List list;
    private int cursor;

    public ListIterator(List list) {
      this.list = list;
    }

    @Override
    public boolean hasNext() {
      return cursor < list.size();
    }

    @Override
    public Object next() {
      return list.get(cursor++);
    }
  }
}
