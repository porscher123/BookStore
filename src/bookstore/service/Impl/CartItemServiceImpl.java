package bookstore.service.Impl;

import bookstore.dao.CartItemDAO;
import bookstore.pojo.Book;
import bookstore.pojo.Cart;
import bookstore.pojo.CartItem;
import bookstore.pojo.User;
import bookstore.service.BookService;
import bookstore.service.CartItemService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 如果购物城中已经存在该图书, 则该图书数量+1
// 否则, 否则在我的购物车中新增一个该图书的CartItem项, 数量为1
public class CartItemServiceImpl implements CartItemService {
    private CartItemDAO cartItemDAO = null;
    private BookService bookService = null;
    @Override
    public void addCartItem(CartItem cartItem) throws SQLException {
        cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public void updateCartItem(CartItem cartItem) throws SQLException {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void addOrUpdateCartItem(CartItem cartItem, Cart cart) throws SQLException {
        // 判断当前用户的购物车是否由该图书
        if (cart != null) {
            Map<Integer, CartItem> cartItemMap = cart.getCartItemMap();
            if (cartItemMap == null) {
                cartItemMap = new HashMap<>();
            }
            if (cartItemMap.containsKey(cartItem.getBook().getId())) {
                CartItem cartItemTemp = cartItemMap.get(cartItem.getBook().getId());
                cartItemTemp.setBuyCount(cartItemTemp.getBuyCount() + 1);
                updateCartItem(cartItemTemp);
            } else {
                addCartItem(cartItem);
            }
        } else { // 购物车为空
            addCartItem(cartItem);
        }
    }

    @Override
    public Cart getCart(User user) throws SQLException,NoSuchFieldException,
            ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        // 获取user的购物车项了列表
        // 因为数据库中cart_item表的book只存了id, 所以获取到的book只有id属性
        // 没有price属性
        List<CartItem> cartItemList = getCartItemList(user);
        // 构造Map
        Map<Integer, CartItem> cartItemMap = new HashMap<>();
        for (var cartItem : cartItemList) {
            cartItemMap.put(cartItem.getBook().getId(), cartItem);
        }
        // 给cart的属性赋值
        Cart cart = new Cart();
        cart.setCartItemMap(cartItemMap);
        cart.getTotalMoney();
        cart.getTotalCount();
        cart.getTotalBookCount();
        return cart;
    }

    /**
     * 获取指定用户的所有购物车项列表
     * 每个购物车项中的book信息不仅包含id
     * @param user
     * @return
     */
    @Override
    public List<CartItem> getCartItemList(User user) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        List<CartItem> cartItemList = cartItemDAO.getCartItemList(user);
        for (CartItem item : cartItemList) {
            int bookId = item.getBook().getId();
            Book book = bookService.getBookById(bookId);
            item.setBook(book);
            // 调用小计, 使得item的xj属性有值
            item.getXj();
        }
        return cartItemList;
    }

    @Override
    public void delCartItem(CartItem cartItem) throws SQLException {
        cartItemDAO.delCartItem(cartItem);
    }

    @Override
    public void delCart(User user) throws SQLException {
        cartItemDAO.delCart(user);
    }

}
