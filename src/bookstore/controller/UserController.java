package bookstore.controller;

import bookstore.pojo.Book;
import bookstore.pojo.Cart;
import bookstore.pojo.User;
import bookstore.service.BookService;
import bookstore.service.CartItemService;
import bookstore.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserController {
    private UserService userService = null;
    private CartItemService cartItemService = null;
    public String login(HttpSession session, String uname, String pwd, Integer curPage) throws SQLException, NoSuchFieldException, ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        User user = userService.login(uname, pwd);
        // user不空, 则登录成功
        if (user != null) {
            // 查询用户的购物车项
            Cart cart = cartItemService.getCart(user);
            user.setCart(cart);
            session.setAttribute("currUser", user);
            return "redirect:book.do?curPage="+curPage;
        }
        return "user/login";
    }

    public String regist(HttpSession session, HttpServletResponse response, String uname, String pwd, String email, String verifyCode)
            throws SQLException, IOException {
        // 获取验证信息
        Object kaptchaSessionKey = session.getAttribute("KAPTCHA_SESSION_KEY");
        // 验证失败
        if (!verifyCode.equals(kaptchaSessionKey)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script language ='javascript'> alert('验证码错误'); window.location.href='page.do?operate=page&page=user/regist';</script>");
            return null;
        }
        // 验证成功, 进行注册
        userService.regist(new User(uname, pwd, email));
        // 跳转到登录界面进行登录
        return "user/login";
    }

    /**
     * 服务器端对请求发送的信息做校验
     *
     */
    public String ckUname(String uname) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        User user = userService.getUser(uname);
        // 用户名存在, 不可以注册
        if (user != null) {
            return "json:{'uname':'1'}";
            // return "ajax:1";
        }
        return "json:{'uname':'0'}";
        // return "ajax:0";
    }
}
