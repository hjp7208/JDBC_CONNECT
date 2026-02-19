package dbutil.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionTest2 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/jdbc";
        String user = "jdbcuser";
        String password = "jdbcuser";
        
        try(Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println(conn);
            System.out.println("DB 연결 성공");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
