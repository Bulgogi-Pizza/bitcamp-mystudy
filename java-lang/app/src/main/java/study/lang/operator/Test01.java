package study.lang.operator;

// 실습
// - 산술 연산자를 사용하여 연산을 수행한 후 결과를 확인하라.

// 학습 내용
// - 정수와 정수의 연산 결과는 정수이다.
// - 부동소수점과 부동소수점의 연산 결과는 부동소수점이다.

public class Test01 {
  public static void main(String[] args) {
    // 정수의 연산
    System.out.println(100 + 24);
    System.out.println(100 - 24);
    System.out.println(100 * 24);
    System.out.println(100 / 24);
    System.out.println(100 % 24);

    // 부동소수점의 연산 (소수점 최소 한 자리)
    System.out.println(100.0 + 24);
    System.out.println(100.0 - 24);
    System.out.println(100.0 * 24);
    System.out.println(100.0 / 24);
    System.out.println(100.0 % 24);

  }
}
