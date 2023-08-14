package bookstore.dao;

import bookstore.pojo.OrderItem;

import java.sql.SQLException;

public interface OrderItemDAO {

    void addOrderItem(OrderItem orderItem) throws SQLException;
}
