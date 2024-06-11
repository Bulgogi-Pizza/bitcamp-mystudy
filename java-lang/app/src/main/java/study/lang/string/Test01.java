package study.lang.string;

public class Test01 {
  public static void main(String[] args) {
    String s = new String("aaa");
    String s2 = new String("aaa");
    String test = new String("aaa");

    String s3 = "aaa";
    String s4 = "aaa";

    System.out.println(s == s2);    //false
    System.out.println(s == s3);    //false
    System.out.println(s3 == s4);   //true
    System.out.println(test == s4);   //true
    System.out.println(test == s3);   //true

    s3 = "hi";

    System.out.println(s3 + s4 + test);
    System.out.println(s3 == s4);   //true
    System.out.println(test == s4);   //true
    System.out.println(test == s3);   //true


  }
}
