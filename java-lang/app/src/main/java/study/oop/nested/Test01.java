package study.oop.nested;

public class Test01 {

  interface Printer {
    void print();
  }

  interface Printer2 {
    void print();
    void print2(int num);
  }

  public static void main(String[] args) {

    Printer obj = new Printer() {
      @Override
      public void print() {
        System.out.println("Hello!");
      }
    };

    Printer obj2;
    obj2 = new Printer() {
      @Override
      public void print() {
        System.out.println("Hello2!");
      }
    };

    class Obj3 implements Printer {
      @Override
      public void print() {
        System.out.println("Hello3!");
      }
    }
    Obj3 obj3 =new Obj3();


    Printer obj4;
    obj4 = () -> {System.out.println("Hello4!");};

    Printer2 obj5;
    obj5 = (100) -> {System.out.println("Hello5!");};
    //(100) -> {System.out.printf("Hello5! %d", num);};

    obj.print();
    obj2.print();
    obj3.print();
    obj4.print();


  }

}
