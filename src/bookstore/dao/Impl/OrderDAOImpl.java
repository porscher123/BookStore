package bookstore.dao.Impl;

import bookstore.dao.OrderDAO;
import bookstore.pojo.OrderBean;
import bookstore.pojo.User;
import myssm.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl extends BaseDao implements OrderDAO {

    @Override
    public void addOrderBean(OrderBean orderBean) throws Exception {
        String sql = "insert into t_order values(0,?,?,?,?,?);";
        int newkey = super.executeUpdate(sql, orderBean.getOrderNo(), orderBean.getOrderDate(),
                orderBean.getOrderUser().getId(), orderBean.getOrderMoney(), orderBean.getOrderStatus());
        System.out.println(newkey);
        orderBean.setId(newkey);
    }

    @Override
    public List<OrderBean> getOrderList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_order where orderUser = ?;";
        return super.executeQuery(OrderBean.class, sql, user.getId());
    }


    /**
     * 查询某个订单的所有书的总量
     * @param orderBean
     * @return
     * @throws Exception
     */
    @Override
    public Integer getOrderTotalBookCount(OrderBean orderBean) throws Exception {
        String sql = "select sum(t2.buyCount)\n" +
                "from t_order as t1 join t_order_item as t2\n" +
                "on t1.id = t2.orderBean\n" +
                "where t1.id = ? and t1.orderUser = ?\n" +
                "group by t2.orderBean;";
        ArrayList<Object> objects = super.executeComplexQuery(sql, orderBean.getId(), orderBean.getOrderUser().getId());
        if (objects.isEmpty()) return 0;
        return ((BigDecimal) super.executeComplexQuery(sql, orderBean.getId(), orderBean.getOrderUser().getId()).get(0)).intValue();
    }
}
