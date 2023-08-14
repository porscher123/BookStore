package bookstore.pojo;

import java.math.BigDecimal;

public class CartItem {
    private Integer id;

    private Book book;

    private Integer buyCount;

    private User userBean;

    private Double xj;

    public void setXj(Double xj) {
        this.xj = xj;
    }

    public CartItem() {
    }

    public CartItem(Integer id) {
        this.id = id;
    }

    public CartItem(Integer id, Integer buyCount) {
        this.id = id;
        this.buyCount = buyCount;
    }

    public CartItem(Book book, Integer buyCount, User userBean) {
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public CartItem(Integer id, Book book, Integer buyCount, User userBean) {
        this.id = id;
        this.book = book;
        this.buyCount = buyCount;
        this.userBean = userBean;
    }

    public Double getXj() {
        BigDecimal bigDecimalPrice = new BigDecimal(getBook().getPrice()+"");
        BigDecimal bigDecimalBuyCount = new BigDecimal(this.buyCount+"");
        BigDecimal multiply = bigDecimalPrice.multiply(bigDecimalBuyCount);
        xj = multiply.doubleValue();
        return xj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getBuyCount() {
        return buyCount;
    }

    public void setBuyCount(Integer buyCount) {
        this.buyCount = buyCount;
    }

    public User getUserBean() {
        return userBean;
    }

    public void setUserBean(User user) {
        this.userBean = user;
    }
}
