package bookstore.service;

import bookstore.pojo.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface UserService {
    User login(String uname, String pwd) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void regist(User user) throws SQLException;

    User getUser(String uname) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
