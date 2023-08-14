package bookstore.dao;

import bookstore.pojo.Book;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public interface BookDAO {
//    List<Book> getBookList(Integer minPrice, Integer maxPrice, Integer pageNo) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;
    List<Book> getBookList(Integer page) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;

    Book getBookById(Integer id) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException;


    Integer getBookCount() throws Exception;
}
