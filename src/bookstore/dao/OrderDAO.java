package bookstore.dao;

import bookstore.pojo.OrderBean;
import bookstore.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    void addOrderBean(OrderBean orderBean) throws Exception;

    List<OrderBean> getOrderList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;


    Integer getOrderTotalBookCount(OrderBean orderBean) throws Exception;

}
