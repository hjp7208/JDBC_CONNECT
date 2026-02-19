package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonVO;

public class DBSelectTest3 {
    public static void main(String[] args) {
        // DB연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO> list = new ArrayList<>();
        // DB 작업
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM person WHERE id >= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 0);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) { // rs.next()의 반환값은 boolean
                PersonVO vo = new PersonVO(
                    rs.getString("user_id"),
                    rs.getString("user_pw"),
                    rs.getString("user_name"),
                    rs.getString("user_email"),
                    rs.getString("phone1"),
                    rs.getString("phone2"),
                    rs.getByte("age"),
                    rs.getString("address1"),
                    rs.getString("address2")
                );
                vo.setId(rs.getLong("id"));
                vo.setRegDate(rs.getTimestamp("reg_date"));
                vo.setModDate(rs.getTimestamp("mod_date"));
                list.add(vo);
            }
            list.stream().forEach(System.out::println);  
        } catch (Exception e) {
            System.out.println("불러오기 실패");
            e.printStackTrace();
        }
    }
}
