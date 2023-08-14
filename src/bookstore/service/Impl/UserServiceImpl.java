package bookstore.service.Impl;

import bookstore.dao.UserDAO;
import bookstore.pojo.User;
import bookstore.service.UserService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO = null;
    @Override
    public User login(String uname, String pwd) throws SQLException, NoSuchFieldException, ClassNotFoundException,
            InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return userDAO.getUser(uname, pwd);
    }

    @Override
    public void regist(User user) throws SQLException {
        userDAO.addUser(user);
    }

    @Override
    public User getUser(String uname) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return userDAO.getUser(uname);
    }
}
