package bookstore.service.Impl;

import bookstore.dao.CartItemDAO;
import bookstore.dao.OrderDAO;
import bookstore.dao.OrderItemDAO;
import bookstore.pojo.CartItem;
import bookstore.pojo.OrderBean;
import bookstore.pojo.OrderItem;
import bookstore.pojo.User;
import bookstore.service.OrderService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDAO orderDAO = null;
    private OrderItemDAO orderItemDAO = null;
    private CartItemDAO cartItemDAO = null;

    /**
     * todo:
     *      1.项订单表中添加一条记录
     *      2.订单详情表添加记录
     *      3.购物车项表中需要删除
     * @param orderBean
     */
    @Override
    public void addOrderBean(OrderBean orderBean) throws Exception {
        // 添加订单
        orderDAO.addOrderBean(orderBean);
        // 添加所有订单项
        // 通过orderBean的getOrderItemList为null
        // 可以根据用户的购物车项转换为购物车项
        User currUser = orderBean.getOrderUser();
        Map<Integer, CartItem> cartItemDMap = currUser.getCart().getCartItemMap();
        for (CartItem cartItem : cartItemDMap.values()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBook(cartItem.getBook());
            orderItem.setBuyCount(cartItem.getBuyCount());
            orderItem.setOrderBean(orderBean);
            orderItemDAO.addOrderItem(orderItem);
        }

        // 删除用户购物车中的所有购物车项
        for (CartItem cartItem : cartItemDMap.values()) {
            cartItemDAO.delCartItem(cartItem);
        }
    }

    @Override
    public List<OrderBean> getOrderList(User user) throws Exception {
        List<OrderBean> orderList = orderDAO.getOrderList(user);
        for (OrderBean order : orderList) {
            Integer orderTotalBookCount = orderDAO.getOrderTotalBookCount(order);
            order.setTotalBookCount(orderTotalBookCount);
        }
        return orderList;
    }
}
