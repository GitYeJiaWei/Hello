package com.hello;

import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class HelloServlet extends HttpServlet {
    public HelloServlet(){
        super();
    }

    public void init() throws ServletException{
        // 初始化代码...
    }

    //处理post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    //处理get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受客户端的用户名和密码,把字符编码改成utf-8
        //String account =new String(request.getParameter("account").getBytes("ISO8859-1"),"UTF-8");
        String account = request.getParameter("account");
        String password = request.getParameter("password");

        // 为名字和密码创建 Cookie
        Cookie name = new Cookie("name",account);
        Cookie pass = new Cookie("pass",password);
        // 为两个 Cookie 设置过期日期为 24 小时后
        name.setMaxAge(60*2);
        pass.setMaxAge(60*2);
        // 在响应头中添加两个 Cookie
        response.addCookie(name);
        response.addCookie(pass);

        //创建用户
        User user = new User(account,password);
        try {
            if (user.isExist()){
                //登陆成功
                response.sendRedirect("/Welcome");
            }else{
                //登陆失败
                response.sendRedirect("/Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
