package study.lang.operator;

// Java는 byte, short, char를 산술연산할 때 int로 변환한 후 수행한다.
// 같은 타입끼리만 연산할 수 있다.
// int와 int의 연산 결과는 int이다.
// 타입이 다르면 컴파일러는 같은 타입으로 자동 변환한다. (implicit type conversion)
// (byte, char, short) --> int --> long --> float --> double
// 개발자가 명시적으로 타입을 변환할 수 있다. (explicit type conversion)

public class Test03 {
  public static void main(String[] args) {
    byte b = 1;
    char c = 2;
    short s = 3;
    int i = 4;
    long l = 5;
    float f = 6.0f;
    double d = 7.0;

    int r = b + c + s;
    long r2 = i + l;
    //long r3 = f;
    float r4 = l; //값이 짤릴 수 있다.
  }
}
