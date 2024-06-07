package study.lang.operator;

// Java는 byte, short, char를 산술연산할 때 int로 변환한 후 수행한다.
// 같은 타입끼리만 연산할 수 있다.
// int와 int의 연산 결과는 int이다.
// 타입이 다르면 컴파일러는 같은 타입으로 자동 변환한다. (implicit type conversion)
// (byte, char, short) --> int --> long --> float --> double
// 개발자가 명시적으로 타입을 변환할 수 있다. (explicit type conversion)

public class Test02 {
  public static void main(String[] args) {
    byte b1 = 100;
    byte b2 = 20;
    byte b3;

    // 다음 코드를 완성하라.
    // - b3 변수에 b1 + b2 값을 넣고 출력해보라!

    b3 = 100 + 20; // 리터럴 끼리의 연산 결과는 리터럴이다.
    b3 = (byte) (b1 + b2); // byte, char, short는 int로 변환된 후 실행된다.
    System.out.println(b3);

    int r = b1 + b2;

    char c = 20;
    short s = 30;
    //short r2 = c + s;

    int i1 = 2_100_000_000;
    int i2 = 2_100_000_000;
    int i3 = i1 + i2;
    System.out.println(i3);

    long r3 = (long)i1 + i2;
    System.out.println(r3);
  }
}
