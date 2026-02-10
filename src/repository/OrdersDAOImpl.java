package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.orders.OrdersVO;

public class OrdersDAOImpl implements Orders {

    @Override
    public boolean deleteOrder(long id) {
        return false;
    }

    @Override
    public boolean insertOrder(OrdersVO order) {
        // 결과값 저장을 위한 변수 설정
        int result = 0;
        try (Connection conn = DBUtil.getConnection()) {
            String sql = "INSERT INTO orders(order_list, order_num, price, "
                       + "date, user_id "
                       + "VALUES(?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNum());
            pstmt.setInt(3, order.getPrice());
            pstmt.setTimestamp(4, order.getDate());
            pstmt.setString(5, order.getUserId());
            result = pstmt.executeUpdate();

            if(result != 0) {
                System.out.println("주문 정보 저장 성공.");
                return true;
            } else System.out.println("주문 정보 저장 실패.");
        } catch (SQLException e) {
            System.out.println("DB작업 실패");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean modifyOrder(OrdersVO order) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<OrdersVO> ordersList() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<OrdersVO> ordersSearch(String userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<OrdersVO> ordersSearch(int orderNum) {
        // TODO Auto-generated method stub
        return Optional.empty();
    }

    @Override
    public List<OrdersVO> ordersSearchDate(String date) {
        // TODO Auto-generated method stub
        return null;
    }

}
