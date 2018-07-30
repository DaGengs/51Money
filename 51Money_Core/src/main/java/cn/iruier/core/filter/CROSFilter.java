package cn.iruier.core.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@WebFilter(filterName = "cros",urlPatterns = "/*")
public class CROSFilter implements Filter {
    //初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    //过滤
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response=(HttpServletResponse)servletResponse;
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type,x-requested-with,Authorization");
        response.setHeader("Access-Control-Allow-Credentials","true");
        filterChain.doFilter(servletRequest,response);
    }
    //销毁
    @Override
    public void destroy() {

    }
}
