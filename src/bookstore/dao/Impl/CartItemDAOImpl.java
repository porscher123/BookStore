package bookstore.dao.Impl;

import bookstore.dao.CartItemDAO;
import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import myssm.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class CartItemDAOImpl extends BaseDao implements CartItemDAO {
    @Override
    public void addCartItem(CartItem cartItem) throws SQLException {
        String sql = "insert into t_cart_item(book,buyCount,userBean)" +
                " values(?,?,?);";
        super.executeUpdate(sql, cartItem.getBook().getId(),
                cartItem.getBuyCount(), cartItem.getUserBean().getId());
    }

    // 根据已经在表中的CartItem的id修改其购买数量buyCount
    @Override
    public void updateCartItem(CartItem cartItem) throws SQLException {
        String sql = "update t_cart_item set buyCount = ? where id = ?;";
        super.executeUpdate(sql, cartItem.getBuyCount(), cartItem.getId());
    }

    // 获取特定用户所有的购物车项
    @Override
    public List<CartItem> getCartItemList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_cart_item" +
                " where userBean = ?;";
        return super.executeQuery(CartItem.class, sql, user.getId());
    }

    @Override
    public void delCartItem(CartItem cartItem) throws SQLException {
        String sql = "delete from t_cart_item" +
                " where id = ?;";
        super.executeUpdate(sql, cartItem.getId());
    }

    @Override
    public void delCart(User user) throws SQLException {
        String sql = "delete from t_cart_item where userBean = ?";
        super.executeUpdate(sql,user.getId());
    }


}
