package dbutil.test;

import java.util.Scanner;

import domain.users.UserVO;
import repository.Users;
import repository.UsersDAOImpl;

public class RepositoryTest {
    private static Users rep = new UsersDAOImpl();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in, "cp949");
        // test data
        /*
        INSERT INTO
        UserVO testData = UserVO.builder()
                            .userId("test111")
                            .userPw("test11")
                            .userName("test111")
                            .userEmail("test111@gmail.com")
                            .build();
        */
        // SELECT * FROM person WHERE user_id='test111' AND user_name='test111';
        // System.out.println(rep.userSearch("test111", "test111"));
        // System.out.println(rep.userSearch("test111@gmail.com"));
        // rep.userAll();

        // 기존 데이터를 로드 -> 수정 -> 적용
        /*
        UserVO before = rep.userSearch("test111@gmail.com").get(0);
        UserVO after = before;
        after.setAge((byte)50);
        after.setAddress1("주소1");
        rep.userMod(before, after);
        */

        // DELETE FROM person WHERE id=57;
        UserVO del = UserVO.builder().id(scan.nextLong()).build();
        rep.userDel(del);
        scan.close();
    }
}
