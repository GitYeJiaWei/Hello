package com.hello;

import com.model.User;
import com.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.*;
import java.io.IOException;
import java.io.PrintWriter;

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
            JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                    "账号或密码不能为空!\n请重新输入！", "系统信息", JOptionPane.WARNING_MESSAGE);
            response.sendRedirect("register.jsp");
            return;
        }
        if (!password.equals(repassword)){
            JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                    "两次密码不相同!\n请重新输入！","系统信息",JOptionPane.WARNING_MESSAGE);
            response.sendRedirect("register.jsp");
        }else{

            //创建用户
            User user = new User(account,password);
            UserDao userDao = new UserDao();
            //子类调用父类
            userDao.getConn();

            if (userDao.addUser(user)){
                //登陆成功
                JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                        "注册成功！","系统信息",JOptionPane.WARNING_MESSAGE);
                response.sendRedirect("index.jsp");
            }else{
                //登陆失败
                JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                        "注册失败！","系统信息",JOptionPane.WARNING_MESSAGE);
                response.sendRedirect("register.jsp");
            }
        }
    }
}
