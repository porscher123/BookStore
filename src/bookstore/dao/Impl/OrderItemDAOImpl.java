package bookstore.dao.Impl;

import bookstore.dao.OrderItemDAO;
import bookstore.pojo.OrderItem;
import myssm.utils.BaseDao;

import java.sql.SQLException;

public class OrderItemDAOImpl extends BaseDao implements OrderItemDAO {
    @Override
    public void addOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item values(0,?,?,?);";
        super.executeUpdate(sql, orderItem.getBook().getId(), orderItem.getBuyCount(),
                orderItem.getOrderBean().getId());
    }
}
