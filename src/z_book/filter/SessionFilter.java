package z_book.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

//
//@WebFilter(
//    urlPatterns = {"*.do", "*.html"},
//    // 白名单集合以都好分割
//    initParams = {
//        @WebInitParam(name = "bai", value = "/page.do?operate=page&page=user/login,/user.do?null")
//    }
//)
public class SessionFilter implements Filter {
    List<String> whiteList = null;
    @Override
    public void init(FilterConfig config) {
        // 初始化白名单列表
        String bai = config.getInitParameter("bai");
        String[] baiArr = bai.split(",");
        this.whiteList = Arrays.asList(baiArr);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        // http://localhost:8080/page.do?operate=page&page=user/login
        // System.out.println("request.getRequestURL() = " + request.getRequestURL());
        // System.out.println("request.getRequestURI() = " + request.getRequestURI());
        // System.out.println("request.getQueryString() = " + request.getQueryString());
        String requestURI = request.getRequestURI();
        String queryString = request.getQueryString();
        String str = requestURI + "?" + queryString;
        // 如果请求的资源地址在白名单中, 正常放行
        if (whiteList.contains(str)) {
            filterChain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            Object currUserObj = session.getAttribute("currUser");
            // 如果没有登录, 则转移到登录界面
            if (currUserObj == null) {
                response.sendRedirect("page.do?operate=page&page=user/login");
            } else { // 否则放行
                filterChain.doFilter(request, response);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
