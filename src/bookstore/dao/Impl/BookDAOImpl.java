package bookstore.dao.Impl;

import bookstore.dao.BookDAO;
import bookstore.pojo.Book;
import myssm.utils.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

public class BookDAOImpl extends BaseDao implements BookDAO {
//    @Override
//    public List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
//        String sql = "select * from t_book " +
//                "where price between ? and ? limit ? offset ?";
//        return super.executeQuery(Book.class, sql, minPrice, maxPrice);
//    }

    @Override
    public List<Book> getBookList(Integer page) throws SQLException, NoSuchFieldException,
            ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_book where bookState = 0 limit 10 offset ?;";
        int offset = (page - 1) * 10;
        return super.executeQuery(Book.class, sql, offset);
    }

    @Override
    public Book getBookById(Integer id) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from t_book" +
                " where id = ?;";
        List<Book> books = super.executeQuery(Book.class, sql, id);
        if (books.isEmpty()) return null;
        return books.get(0);
    }

    @Override
    public Integer getBookCount() throws Exception {
        String sql = "select count(1) from t_book;";
        return ((Long)super.executeComplexQuery(sql).get(0)).intValue();
    }
}
