window.onload = () => {
    var vue = new Vue({
        'el' : '#div_cart',
        data : {
            cart : {}
        },
        methods: {
            getCart : function () {
                // axios内部都是axios的命名空间
                // 要引用data数据要用vue点出来
                axios({
                    method : "POST",
                    url : "cart.do",
                    params : {
                        operate : "cartInfo"
                    }
                }).then(function (value) {
                    var cart = value.data;
                    vue.cart = cart;

                }).catch(function (reason){

                });
            },
            editCart : function (cartItemId, buyCount) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "editCart",
                        cartItemId: cartItemId,
                        buyCount: buyCount
                    }
                }).then(function (value) {
                    // 重新查询购物车
                    vue.getCart();
                }).catch(function (reason){

                });
            },
            delCartItem: function (carItemId) {
                axios({
                    method: "POST",
                    url: "cart.do",
                    params: {
                        operate: "delCartItem",
                        cartItemId: carItemId
                    }
                }).then(function (value) {
                    // 重新查询购物车
                    vue.getCart();
                }).catch(function (reason){

                });
            }
        },
        mounted: function () {
            this.getCart();
        }
    });
}

