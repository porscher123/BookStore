package bookstore.controller;

import bookstore.pojo.Book;
import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import bookstore.service.CartItemService;
import com.google.gson.Gson;

import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class CartController {

    private CartItemService cartItemService = null;

    // 将指定bookId的图书添加到当前用户的购物车中
    public String addCart(Integer bookId, HttpSession session) throws SQLException {
        User user = (User) session.getAttribute("currUser");
        CartItem cartItem = new CartItem(new Book(bookId), 1, user);
        cartItemService.addOrUpdateCartItem(cartItem, user.getCart());
        return "redirect:cart.do";
    }
    // 加载当前用户的购物车信息
    public String index(HttpSession session) throws SQLException,
            NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        // 更新用户的cart信息, 并覆盖原有user
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);
        user.setCart(cart);
        session.setAttribute("currUser", user);
        // 跳转到cart.html: 购物车页面
        return "cart/cart";
    }

    public String editCart(Integer cartItemId, Integer buyCount) throws SQLException {
        cartItemService.updateCartItem(new CartItem(cartItemId, buyCount));
        return "";
    }

    public String cartInfo(HttpSession session) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        User user = (User) session.getAttribute("currUser");
        Cart cart = cartItemService.getCart(user);

        Gson gson = new Gson();
        String jsonCart = gson.toJson(cart);
        System.out.println("json:" + jsonCart);
        return "json:" + jsonCart;
    }
    public String delCartItem(Integer cartItemId) throws SQLException {
        cartItemService.delCartItem(new CartItem(cartItemId));
        return "cart/cart";
    }

    public String delCart(HttpSession session) throws SQLException {
        User user = (User) session.getAttribute("currUser");
        cartItemService.delCart(user);
        return "cart/cart";
    }
}
