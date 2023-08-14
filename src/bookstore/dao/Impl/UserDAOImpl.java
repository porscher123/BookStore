package bookstore.dao.Impl;

import bookstore.dao.UserDAO;
import bookstore.pojo.User;
import myssm.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl extends BaseDao implements UserDAO {
    @Override
    public User getUser(String uname, String pwd) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_user " +
                "where uname like ? and pwd like ?;";
        List<User> users = super.executeQuery(User.class, sql, uname, pwd);
        if (users.isEmpty()) return null;
        return users.get(0);

    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into t_user values(0,?,?,?,0);";
        super.executeUpdate(sql, user.getUname(), user.getPwd(),
                user.getEmail());
    }


    /**
     * 查看用户是否存在
     * @param user
     * @return
     */
    @Override
    public User getUser(String uname) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_user " +
                "where uname = ?;";
        List<User> users = super.executeQuery(User.class, sql, uname);
        if (users.isEmpty()) return null;
        return users.get(0);
    }
}
