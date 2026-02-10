package repository;

import java.util.List;
import java.util.Optional;

import domain.orders.OrdersVO;

public class OrdersDAOImpl implements Orders {

    @Override
    public boolean deleteOrder(long id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean insertOrder(OrdersVO order) {
        // TODO Auto-generated method stub
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
