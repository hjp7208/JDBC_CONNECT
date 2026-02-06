package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import domain.users.UserVO;

public class UsersDAOImpl implements Users {
    // 필드 선언...
    private String url = "jdbc:mysql://localhost:3306/jdbc";
    private String dbuser = "jdbcuser";
    private String password = "jdbcuser";
    protected int result = 0;

    @Override
    public int userAdd(UserVO user) {
        // SQL 작성
        String sql = "INSERT INTO person(user_id, user_pw, user_name, user_email, "
                    + "phone1, phone2, age, address1, address2) "
                    + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // INSERT 작업
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) { // PreparedStatement 객체 생성        
            
            // SQL의 ?에 대한 데이터 추가.
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getUserPw());
            pstmt.setString(3, user.getUserName());
            pstmt.setString(4, user.getUserEmail());
            pstmt.setString(5, user.getPhone1());
            pstmt.setString(6, user.getPhone2());
            pstmt.setByte(7, user.getAge());
            pstmt.setString(8, user.getAddress1());
            pstmt.setString(9, user.getAddress2());
            System.out.println(pstmt);

            result = pstmt.executeUpdate();
            System.out.println(result != 0 ? "데이터 입력 성공" : "데이터 입력 실패");
            return result;  // 실행 성공 시 result 반환하고 종료.
        } catch (SQLException e) {
            System.out.println("DB 작업 실패");
            e.printStackTrace();
        }
        return 0;   // catch 발생 시 0 반환.
    }

    @Override
    public List<UserVO> userAll() {
        // SELECT * 작업
        // SQL
        String sql = "SELECT * FROM person";

        List<UserVO> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {
            
            while(rs.next()) {
                list.add(UserVO.builder()
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
                            .build());
            }
            list.forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println("DB 작업 실패");
        }
        return list;
    }

    @Override
    public int userDel(UserVO user) {
        String sql = "DELETE FROM person WHERE id = ?";
        // DELECT 작업
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setLong(1, user.getId());;
        } catch(SQLException e) {
            System.out.println("DB 작업 실패");
        }
        return result;
    }

    @Override
    public int userMod(UserVO befor, UserVO after) {
        String sql = "UPDATE person SET user_id=?, user_pw=?, user_name=?, user_email=?, "
                   + "phone1=?, phone2=?, age=?, address1=?, address2=?, "
                   + "mod_date=? WHERE id=?";
        // UPDATE 작업
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, after.getUserId());
            pstmt.setString(2, after.getUserPw());
            pstmt.setString(3, after.getUserName());
            pstmt.setString(4, after.getUserEmail());
            pstmt.setString(5, after.getPhone1());
            pstmt.setString(6, after.getPhone2());
            pstmt.setByte(7, after.getAge());
            pstmt.setString(8, after.getAddress1());
            pstmt.setString(9, after.getAddress2());
            pstmt.setTimestamp(10, new Timestamp(System.currentTimeMillis()));
            pstmt.setLong(11, befor.getId());

            result = pstmt.executeUpdate();           
        } catch(SQLException e) {
            System.out.println("DB 작업 실패");
        }
        return result;
    }

    @Override
    public List<UserVO> userSearch(String userId, String userName) {
        String sql = "SELECT * FROM person WHERE user_id=? AND user_name=?";
        // SELECT user_id, user_name FROM 작업
        List<UserVO> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,userId);
            pstmt.setString(2, userName);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(UserVO.builder()
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
                            .build());
            }
        } catch(SQLException e) {
            System.out.println("DB 작업 실패");
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<UserVO> userSearch(String userEmail) {
        String sql = "SELECT * FROM person WHERE user_email=?";
        // SELECT user_email FROM 작업
        List<UserVO> list = new ArrayList<>();
        try(Connection conn = DriverManager.getConnection(url, dbuser, password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, userEmail);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                list.add(UserVO.builder()
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
                            .build());
            }
        } catch(SQLException e) {
            System.out.println("DB 작업 실패");
            e.printStackTrace();
        }
        return list;
    }
}
