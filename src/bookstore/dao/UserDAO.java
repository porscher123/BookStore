package bookstore.dao;

import bookstore.pojo.User;
import myssm.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface UserDAO  {
    User getUser(String uname, String pwd) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    void addUser(User user) throws SQLException;

    User getUser(String uname) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
}
