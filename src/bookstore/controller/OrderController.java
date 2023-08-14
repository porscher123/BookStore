package bookstore.controller;

import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.OrderBean;
import bookstore.pojo.User;
import bookstore.service.CartItemService;
import bookstore.service.OrderService;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderController {
    private OrderService orderService = null;
    private CartItemService cartItemService = null;
    /**
     * 结账
     * @return
     */
    public String chechout(HttpSession session) throws Exception {
//        orderService.addOrderBean();
        OrderBean orderBean = new OrderBean();
        // 设置订单的参数
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowStr = sdf.format(now);
        nowStr.replace("-", "");
        orderBean.setOrderNo(UUID.randomUUID() + "_" +nowStr);

        orderBean.setOrderDate(now);

        User user = (User) session.getAttribute("currUser");
        orderBean.setOrderUser(user);

        orderBean.setOrderMoney(user.getCart().getTotalMoney());
        orderBean.setOrderStatus(0);
        // 添加
        orderService.addOrderBean(orderBean);

        // 解决结完账, 购物车项数量不更新
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        return "index";
    }

    /**
     * 查看订单列表
     * @param session
     * @return
     */
    public String getOrderList(HttpSession session) throws Exception {
        User user = (User) session.getAttribute("currUser");
        List<OrderBean> orderList = orderService.getOrderList(user);
        user.setOrderList(orderList);
        session.setAttribute("currUser", user);
        return "order/order";
    }
}
