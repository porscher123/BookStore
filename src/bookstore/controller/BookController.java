package bookstore.controller;

import bookstore.pojo.Book;
import bookstore.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class BookController {
    private BookService bookService = null;

    public String index(HttpSession session, Integer curPage) throws Exception {
        List<Book> bookList = bookService.getBookList(curPage);
        int bookCount = bookService.getBookCount();
        int pages = (int) Math.ceil(bookCount / 10.0);
        session.setAttribute("bookList", bookList);
        session.setAttribute("bookCount", bookCount);
        session.setAttribute("pages", pages);
        session.setAttribute("curPage", curPage);
        return "index";
    }
}
