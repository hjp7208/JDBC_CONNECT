package domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
Lombok에서 주로 사용하는 어노테이션
@Getter/@Setter: Getter/Setter 생성
@ToString: toString() 생성
@EqualsAndHashCode: equals()와 hashCode() 자동 구현
@Data: Getter,Setter,toString, equalsAndHashCode 어노테이션 모두 포함
@Builder: 복잡 객체 생성을 안정화하는 Builder 패턴을 자동 생성.
@AllArgsConstructor: 필드 전체를 사용한 생성자 자동 생성.
@NoArgsConstructor: 기본 생성자 자동 생성.

#주의점
1. 무분별한 어노테이션의 사용으로 다른 기능과 연결되어 의도하지 않는 동작을 할 수 있음. (@Data 어노테이션 자중)
2. @Builder 사용하면 기본 생성자는 생성되지 않음. 필요한 경우에는 @NoArgsConstructor 사용.
   @AllArgsConstructor는 위에 @NoArgsConstructor를 사용하는 경우에 같이 사용.
3. Lombok에만 의존하면 문제 발생 시 대처불가. -> 사용하지 못하는 경우에 대해 대비할 필요가 있음.
*/
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PersonLom {
    private long id;
    private String userId;
    private String userPw;
    private String userName;
    private String userEmail;
    private String phone1;
    private String phone2;
    private byte age;
    private String address1;
    private String address2;
    private Timestamp regDate;
    private Timestamp modDate;
}
