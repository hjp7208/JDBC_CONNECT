package domain.users;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "userPw")   // "userPw" 제외하고 toString() 생성
@Builder                        // Builder 클래스 사용
@NoArgsConstructor              // 기본 생성자
@AllArgsConstructor             // 모든 필드를 담은 생성자
public class UserVO {
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
