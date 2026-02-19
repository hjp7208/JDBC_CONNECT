package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import dbutil.DBUtil;
import domain.orders.OrdersVO;

public class OrderDAOImplMariaDB implements Orders {
    @Override
    public boolean deleteOrder(long id) {
        boolean result = false;
        // 주문 삭제 (1. 삭제할 주문을 읽어서, 2. id값을 넘겨주세요.)
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "DELETE FROM orders WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            if (pstmt.executeUpdate() != 0)
                result = true;

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return result;
    }

    @Override
    public boolean insertOrder(OrdersVO order) {
        // 결과값 저장을 위한 변수 설정
        int result = 0;
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "INSERT INTO orders(id, order_list, order_num, price, "
                       + "order_date, user_id "
                       + "VALUES(orders_seq.nextval, ?, ?, ?, ?, ?)";
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
        boolean result = false;
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "UPDATE orders SET orderList=?, orderNum=?, price=?, order_date=s? "
                    + "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, order.getOrderList());
            pstmt.setInt(2, order.getOrderNum());
            pstmt.setInt(3, order.getPrice());
            pstmt.setTimestamp(4, order.getDate());
            pstmt.setLong(5, order.getId());
            if (pstmt.executeUpdate() != 0)
                result = true;
        } catch (Exception e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return result;
    }

    @Override
    public List<OrdersVO> ordersList() {
        // 주문 전체 목록 보기
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "SELECT * FROM orders";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("order_list"))
                        .orderNum(rs.getInt("order_num"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("order_date"))
                        .userId(rs.getString("user_id"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<OrdersVO> ordersSearch(String userId) {
        List<OrdersVO> list = new ArrayList<>();
        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "SELECT * FROM orders WHERE user_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("order_list"))
                        .orderNum(rs.getInt("order)num"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("order_date"))
                        .userId(rs.getString("user_id"))
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("DB연동 실패 : " + e.getMessage());
        }
        return list;
    }

    @Override
    public Optional<OrdersVO> ordersSearch(int orderNum) {
        // 주문 번호를 통한 검색
        Optional<OrdersVO> order = null;

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "SELECT * FROM orders WHERE order_num = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderNum);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                order = Optional.of(
                        OrdersVO.builder()
                                .id(rs.getLong("id"))
                                .orderList(rs.getString("order_list"))
                                .orderNum(rs.getInt("order_num"))
                                .price(rs.getInt("price"))
                                .date(rs.getTimestamp("order_date"))
                                .userId(rs.getString("user_id"))
                                .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return order;
    }

    @Override
    public List<OrdersVO> ordersSearchDate(String date) {
        List<OrdersVO> list = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection(DBUtil.MARIADB)) {
            String sql = "SELECT * FROM orders WHERE order_date > ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            // date는 SimpleDateFormat을 이용한 날짜 형식의 문자열로
            pstmt.setString(1, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(OrdersVO.builder()
                        .id(rs.getLong("id"))
                        .orderList(rs.getString("order_list"))
                        .orderNum(rs.getInt("order_num"))
                        .price(rs.getInt("price"))
                        .date(rs.getTimestamp("order_date"))
                        .userId(rs.getString("user_id"))
                        .build());
            }

        } catch (Exception e) {
            System.out.println("DB 연동 실패 : " + e.getMessage());
        }

        return list;
    }
}
