package study.lang.variable;

public class Test01 {
  public static void main(String[] args) {
    // 실습
    // primitive type의 변수를 선언하라.
    // 각 변수의 최소값, 최대값을 할당하라.
    // 각 변수의 최소값, 최대값을 범위를 벗어나는 값을 넣은 후 오류를 확인하

    // byte b1 = -128;
    // byte b2 = 127;

    byte b1 = -128;
    byte b2 = 127;

    short s1 = -32768;
    short s2 = 32767;

    int i1 = -2147483648;
    int i2 = 2147483647;

    long l1 = -9223372036854775808L;
    long l2 = 9223372036854775807L;

    float f1 = 1.4E-45f;
    float f2 = 3.4028235E38f;

    double d1 = 4.9E-324;
    double d2 = 1.7976931348623157E308;

    char c1 = 0;
    char c2 = 65535;

    boolean bool1 = false;
    boolean bool2 = true;

    System.out.println(--b1);
    System.out.println(++b2);
    System.out.println(--s1);
    System.out.println(++s2);
    System.out.println(--i1);
    System.out.println(++i2);
    System.out.println(--l1);
    System.out.println(++l2);
    System.out.println(--f1);
    System.out.println(++f2);
    System.out.println(--d1);
    System.out.println(++d2);
    System.out.println(--c1);
    System.out.println(++c2);
    System.out.println(bool1);
    System.out.println(bool2);


    // 다음은 값을 저장할 메모리의 크기 때문에 발생한 오류가 아니다.
    // 리터럴 크기의 문제다.
    // 정수 값 뒤에 L 또는 l을 붙이지 않은 리터럴은 4바이트 크기를 의미하다.
    // 그런데 4바이트를 넘어서는 값을 리터럴로 표현했기 때문에 오류가 발생한 것이다.
    // i = -2147483649; // 컴파일 오류!
    // i = 2147483648; // 컴파일 오류!

    // 해결책?
    // - 4 byte 크기를 벗어나는 정수를 표기할 때는 반드시 숫자 뒤에 L 또는 l을 붙여야 한다.
    // i = -2147483649L; // 컴파일 오류가 발생하는 이유? 메모리의 크기가 4바이트라서 값을 저장할 수 없다.
    // i = 2147483648L; // 컴파일 오류!

    // 8바이트 크기의 정수 값을 담을 메모리 준비
    // long l;

    // 8바이트 메모리에 담을 수 있는 최소/최대 정수값
    // l = -9223372036854775808L; // 10000000 00000000 00000000 00000000 00000000 00000000 00000000
    // 00000000
    // l = 9223372036854775807L; // 01111111 11111111 11111111 11111111 11111111 11111111 11111111
    // 11111111
  }
}
