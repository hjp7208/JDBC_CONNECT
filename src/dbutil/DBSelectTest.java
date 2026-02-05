package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBSelectTest {
    public static void main(String[] args) {
        // 1. Connection 연결 객체 생성(DriverManager.getConnection())
        // 2. SQL 작성
        // 3. Connection 객체에서 Statement 객체 생성.
        // 4. 생성된 Statement 객체를 이용해서 SQL 실행(Insert)
        // 5. 이후 작업이 있으면 처리, 없으면 종료

        // 연결을 위한 정보 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            // 2. SQL 작성
            String sql = "SELECT * FROM person";

            // 3. Statement 객체 생성, ResultSet 객체 생성(Query 결과 받을 객체)
            Statement stmt = conn.createStatement();
            ResultSet rs = null;
            // 4. SQL을 실행
            // Select의 실행은 executeQuery(sql) 사용
            // ResultSet 객체로 반환
            rs = stmt.executeQuery(sql);
            // 5. 결과 확인.
            while(rs.next()) { // rs.next()의 반환값은 boolean
                System.out.println("id: " + rs.getLong("id"));
                System.out.println("user_id: " + rs.getString("user_id"));
                System.out.println("user_pw: " + rs.getString("user_pw"));
                System.out.println("user_email: " + rs.getString("user_email"));
                System.out.println("phone: " + rs.getString("phone1")
                                            + "-" + rs.getString("phone2"));
                System.out.println("age: " + rs.getByte("age"));
                System.out.println("address1: " + rs.getString("address1"));
                System.out.println("address2: " + rs.getString("address2"));
                System.out.println("reg_date: " + rs.getTimestamp("reg_date"));
                System.out.println("mod_date: " + rs.getTimestamp("mod_date"));
            } // else {
                // System.out.println("저장된 레코드가 없음.");
            // }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
