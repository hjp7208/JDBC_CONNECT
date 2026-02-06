package dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import domain.PersonRe;
import domain.PersonVO2;

public class DBSelectTest5 {
    public static void main(String[] args) {
        // DB연결을 위한 값을 생성
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";

        List<PersonVO2> list = new ArrayList<>();
        // DB 작업
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT * FROM person WHERE id <= ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, 10);

            // SQL 실행
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) { // rs.next()의 반환값은 boolean
                // vo는 변경되지 않음.
                PersonRe vo = new PersonRe(rs.getLong("id"), rs.getString("user_id"), 
                                rs.getString("user_id"), rs.getString("user_name"), rs.getString("user_email"),
                                rs.getString("phone1"), rs.getString("phone2"), rs.getByte("age"),
                                rs.getString("address1"), rs.getString("address2"), rs.getTimestamp("reg_date"),
                                rs.getTimestamp("mod_date")
                );
                // 불변 데이터(immutable)로 받는 역할을 하고
                System.out.println("Record객체" + vo);

                // Stream을 이용한 형변환(Map)
                // 불변 데이터(immutable) -> 가변 데이터(mutable)로 변경
                list.add(new PersonVO2().builder()
                            .id(vo.id())
                            .userId(vo.userId())
                            .userPw(vo.userPw())
                            .userName(vo.userName())
                            .userEmail(vo.userEmail())
                            .phone1(vo.phone1())
                            .phone2(vo.phone2())
                            .age(vo.age())
                            .address1(vo.address1())
                            .address2(vo.address2())
                            .regDate(vo.regDate())
                            .modDate(vo.modDate())
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
