package bookstore.dao;

import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartItemDAO {
    // 新增购物车项
    void addCartItem(CartItem cartItem) throws SQLException;
    // 修改特定购物车项
    void  updateCartItem(CartItem cartItem) throws SQLException;

    List<CartItem> getCartItemList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void delCartItem(CartItem cartItem) throws SQLException;

    void delCart(User user) throws SQLException;

}
