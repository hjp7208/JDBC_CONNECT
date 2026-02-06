package domain;

import java.sql.Timestamp;

// record 객체 선언 - class 대신 record 사용. (Java14 이후)
// 클래스 선언 시에 "()" 사용.
// getter 기본 설정. Builder 클래스 사용 가능.
// 1. 간결한 객체 정의
// 2. 메소드 자동 생성
// 3. 생성자 자동 생성(Builder 객체 사용)
// 4. 불변성(immutable): 객체 안에 있는 필드들은 final 속성 가짐.
public record PersonRe(
    // 멤버 변수 선언 위치
    long id, String userId, String userPw, String userName, String userEmail,
    String phone1, String phone2, byte age, String address1, String address2,
    Timestamp regDate, Timestamp modDate

) {
    
}
