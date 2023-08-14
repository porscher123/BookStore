package bookstore.service;

import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface CartItemService {

    void addCartItem(CartItem cartItem) throws SQLException;
    void updateCartItem(CartItem cartItem) throws SQLException;

    void addOrUpdateCartItem(CartItem cartItem, Cart cart) throws SQLException;

    Cart getCart(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;


    List<CartItem> getCartItemList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void delCartItem(CartItem cartItem) throws SQLException;


    void delCart(User user) throws SQLException;
}
