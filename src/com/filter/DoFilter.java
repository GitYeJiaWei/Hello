package com.filter;


import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 过滤器，拦截器
 */
public class DoFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //获取初始化参数
        String site = filterConfig.getInitParameter("Site");
        // 输出初始化参数
        System.out.println("获取初始化参数"+site);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String name = servletRequest.getParameter("account");
        if (!name.isEmpty()){
            // 把请求传回过滤链
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            //设置返回内容类型
            servletResponse.setContentType("text/html;charset=UTF-8");

            //在页面输出响应信息
            PrintWriter out = servletResponse.getWriter();
            out.print("<b>name为空，请求被拦截，不能访问web资源</b>");
            System.out.println("name为空，请求被拦截，不能访问web资源");
        }

    }

    @Override
    public void destroy() {

    }
}
