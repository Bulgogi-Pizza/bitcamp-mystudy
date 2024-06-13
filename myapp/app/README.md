# 10. CRUD(create, retrieve/read, update, delete) 구현하기

## 학습목표

- 클래스 문법을 이용하여 데이터 타입을 정의할 수 있다.
- 배열을 이용하여 인스턴스 목록을 다룰 수 있다.

## 요구사항

- 회원 CRUD 구현
- 팀 CRUD 구현
- 프로젝트 CRUD 구현
- 게시판 CRUD 구현

## 실행 결과

```
메인> 1
[회원]
1. 등록
2. 목록
3. 조회
4. 변경
5. 삭제
9. 이전 
메인/회원> 1
[등록]
이름? 홍길동
이메일? hong@test.com
암호? 1111
연락처? 010-1111-2222
메인/회원> 2
[목록]
번호 이름 이메일
1 홍길동 hong@test.com
2 임꺽정 leem@test.com
3 유관순 yoo@test.com
4 안중근 ahn@test.com
메인/회원> 3
[조회]
회원번호? 1
이름: 홍길동
이메일? hong@test.com
연락처? 010-1111-2222
메인/회원> 3
[조회]
회원번호? 7
없는 회원입니다.
메인/회원> 4
[변경]
회원번호? 1
이름(홍길동)? 홍길순
이메일(hong@test.com)? hong2@test.com
암호? 2222
연락처(010-1111-2222)? 010-1111-3333
변경하였습니다.
메인/회원> 4
[변경]
회원번호? 7
없는 회원입니다.
메인/회원> 5
[삭제]
회원번호? 1
삭제하였습니다.
메인/회원> 5
[삭제]
회원번호? 7
없는 회원입니다.
```

## 작업

- 1) 

## 소스 파일

- App.java
- User.java
```
public class User {
  String name;
  String email;
  String password;
  String tel;
}
```
  