package com.hello;

import com.model.User;
import com.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            //writer.println("账号或密码不能为空!\n请重新输入！");
            //JOptionPane 是部署在tomcat服务器上，所以执行的也是在tomcat服务器上，
            //在客户端上是不会弹出提示框的，这是服务端的职责，跟客户端没关系，
            //要在客户端弹出提示框要用客户端的技术，js
           /*JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                    "账号或密码不能为空!\n请重新输入！", "系统信息", JOptionPane.WARNING_MESSAGE);
           */
            //response.sendRedirect("register.jsp"); //重定向的方法跳转到register。jsp

            request.setAttribute("error","账号或密码不能为空!");
            request.getRequestDispatcher("register.jsp").forward(request,response);
            return;
        }
        if (!password.equals(repassword)){
            request.setAttribute("error","两次密码不相同!");
            request.getRequestDispatcher("register.jsp").forward(request,response);
        }else{

            //创建用户
            User user = new User(account,password);
            UserDao userDao = new UserDao();
            //子类调用父类
            userDao.getConn();

            if (userDao.addUser(user)){
                writer.println("注册成功");
                writer.println("<br><a href ='index.jsp'>返回登陆</a>");
               /* request.setAttribute("error","注册成功");
                request.getRequestDispatcher("register.jsp").forward(request,response);*/
            }else{
                    request.setAttribute("error","注册失败！账号可能已被注册");
                    request.getRequestDispatcher("register.jsp").forward(request,response);
                }
            }
        }
}
