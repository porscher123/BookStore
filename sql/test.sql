select * from t_cart_item where userBean = 1;




-- 查询用户的订单总金额
select sum(t2.buyCount) as totalbookcount, t1.id
from t_order as t1 join t_order_item as t2
on t1.id = t2.orderBean
where t1.orderUser = 1 and t1.id = 5
group by t2.orderBean;

