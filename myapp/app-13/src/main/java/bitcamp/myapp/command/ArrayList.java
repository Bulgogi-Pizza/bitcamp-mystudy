package bitcamp.myapp.command;

import bitcamp.myapp.vo.User;

public class ArrayList {
  private static final int MAX_SIZE = 100;

  private Object[] list = new Object[MAX_SIZE];
  protected int size = 0;

  public void add(Object obj){
    list[size++] = obj;
  }

  public Object[] remove(int index) {
    if (index < 0 || index >= size) {
      return null;
    }
    Object deletedObj = list[index];
    for (int i = index + 1; i < size; i++){
      list[i - 1] = list[i];
    }
    list[--size] = null;
    return deletedObj;
  }

  public Object[] toArray() {
    Object[] arr = new Object[size];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list[i];
    }
    return arr;
  }

  public int indexOf(Object obj) {
    for (int i = 0; i < size; i++) {
      if (list[i] == obj) {
        return i;
      }
    }
    return -1;
  }

}
