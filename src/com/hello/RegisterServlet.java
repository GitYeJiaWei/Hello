package com.hello;

import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();

        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");

        if(account.trim().isEmpty() || password.trim().isEmpty()){
            writer.println("<script language='javascript'>alert('账号或密码不能为空')</script>");
            return;
        }
        if (!password.equals(repassword)){
            writer.println("<script language='javascript'>alert('两次密码不相同')</script>");
        }else{
            //创建用户
            User user = new User(account,password);
            try {
                if (user.isInsert()){
                    response.sendRedirect("/Welcome");
                }else{
                    response.sendRedirect("/Error");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
