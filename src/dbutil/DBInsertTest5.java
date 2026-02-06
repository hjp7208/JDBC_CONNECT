package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import domain.PersonRe;

public class DBInsertTest5 {
    public static void main(String[] args) {
        // DB연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        // Record 객체의 역할은 값을 변경 없이 받거나 전달
        // Record 객체는 setter가 없음. 변경 불가(immutable).
        PersonRe vo = new PersonRe(0, "testuser10", "testuserPw", "testuserName",
                        "testuser10@test.com", "032", "989-2223",
                        (byte)34, "인천", "어딘가", null, null
        );
        // 변경 불가.
        System.out.println(vo.id());    // vo.id() - getter 메소드

        // DB 작업(PreparedStatement)
        // 1. Connection 객체 생성
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성(PreparedStatement에 사용할)
            String sql = "INSERT INTO person(user_id, user_pw, user_name, user_email, "
                       + "phone1, phone2, age, address1, address2) "
                       + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            // 3. PreparedStatement 객체 생성
            PreparedStatement pstmt = conn.prepareStatement(sql);
            // 4. SQL에 ?에 대한 데이터 추가
            pstmt.setString(1, vo.userId());
            pstmt.setString(2, vo.userPw());
            pstmt.setString(3, vo.userName());
            pstmt.setString(4, vo.userEmail());
            pstmt.setString(5, vo.phone1());
            pstmt.setString(6, vo.phone2());
            pstmt.setByte(7, vo.age());
            pstmt.setString(8, vo.address1());
            pstmt.setString(9, vo.address2());
            System.out.println(pstmt);

            // 5. SQL 실행
        int result = pstmt.executeUpdate();
        if(result != 0) {
            System.out.println("레코드 추가 성공.");
        } else {
            System.out.println("레코드 추가 실패.");
        }
        } catch (Exception e) {
            System.out.println("DB 작업 실패");
            e.printStackTrace();
        }
    }
}
