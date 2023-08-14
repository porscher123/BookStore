function addToCart(bookId) {
    window.location.href='cart.do?operate=addCart&bookId='+bookId;
}

function page(curPage) {
    window.location.href="book.do?curPage="+curPage;
}