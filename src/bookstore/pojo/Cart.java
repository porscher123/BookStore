package bookstore.pojo;

import java.math.BigDecimal;
import java.util.Map;

// 购物车类, 没有与数据库中的表对应
public class Cart {
    // 书的id -> 购物车项
    // 即一个购物车项的集合
    private Map<Integer, CartItem> cartItemMap;
    private Double totalMoney; // 购物车总金额
    // 购物车的购物项的数量
    // 即cartItemMap的size
    // 一个购物车项的书籍数量可能由多个
    private Integer totalCount;




    public Integer getTotalBookCount() {
        totalBookCount = 0;
        for (var item : cartItemMap.values()) {
            totalBookCount += item.getBuyCount();
        }
        return totalBookCount;
    }



    // 书本总数量
    private Integer totalBookCount;






    public Map<Integer, CartItem> getCartItemMap() {
        return cartItemMap;
    }

    public void setCartItemMap(Map<Integer, CartItem> cartItemMap) {
        this.cartItemMap = cartItemMap;
    }

    // 计算购物车的总金额
    public Double getTotalMoney() {
        totalMoney = 0.0;
        BigDecimal bigDecimalTotolMoney = new BigDecimal("0.0");
        // 遍历所有value的集合: 即cartItem的集合
        for (var cartItem : cartItemMap.values()) {
            bigDecimalTotolMoney = bigDecimalTotolMoney.add(new BigDecimal(cartItem.getXj()+""));
        }
        this.totalMoney = bigDecimalTotolMoney.doubleValue();
        return totalMoney;
    }

    public Integer getTotalCount() {
        if (cartItemMap == null) return 0;
        return this.totalCount = cartItemMap.size();

    }
}
