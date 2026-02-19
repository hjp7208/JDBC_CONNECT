package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO2;

public class DBSelectTest4 {
    public static void main(String[] args) {
        // DB연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO2> list = new ArrayList<>();
        // DB 작업
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM person WHERE id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) { // rs.next()의 반환값은 boolean
                list.add(new PersonVO2().builder()
                            .id(rs.getLong("id"))
                            .userId(rs.getString("user_id"))
                            .userPw(rs.getString("user_pw"))
                            .userName(rs.getString("user_name"))
                            .userEmail(rs.getString("user_email"))
                            .phone1(rs.getString("phone1"))
                            .phone2(rs.getString("phone2"))
                            .age(rs.getByte("age"))
                            .address1(rs.getString("address1"))
                            .address2(rs.getString("address2"))
                            .regDate(rs.getTimestamp("reg_date"))
                            .modDate(rs.getTimestamp("mod_date"))
                            .build()
                );
                list.stream().forEach(System.out::println); 
            } 
        } catch (Exception e) {
            System.out.println("불러오기 실패");
            e.printStackTrace();
        }
    }
}
