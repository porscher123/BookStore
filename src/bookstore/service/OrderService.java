package bookstore.service;

import bookstore.pojo.OrderBean;
import bookstore.pojo.OrderItem;
import bookstore.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    void addOrderBean(OrderBean orderBean) throws Exception;

    List<OrderBean> getOrderList(User user) throws Exception;
}
