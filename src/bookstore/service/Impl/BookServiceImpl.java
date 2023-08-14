package bookstore.service.Impl;

import bookstore.dao.BookDAO;
import bookstore.pojo.Book;
import bookstore.service.BookService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookDAO bookDAO = null;
    @Override
    public List<Book> getBookList(Integer page) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return bookDAO.getBookList(page);
    }

    @Override
    public Book getBookById(Integer id) throws SQLException, NoSuchFieldException, ClassNotFoundException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        return bookDAO.getBookById(id);
    }

    @Override
    public Integer getBookCount() throws Exception {
        return bookDAO.getBookCount();
    }
}
