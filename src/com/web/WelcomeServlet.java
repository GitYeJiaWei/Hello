package com.web;

import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 登陆成功提醒，并读取数据库的信息
 */
public class WelcomeServlet extends HttpServlet {
    private Connection conn;   //数据库连接对象
    private Statement state;    //sql语句执行对象
    // JDBC 驱动名及数据库 URL
    private static final String driverName ="com.mysql.cj.jdbc.Driver";
    private static final String url ="jdbc:mysql://localhost:3306/userdb?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";
    private List<User> userList =null;

    public WelcomeServlet(){
        super();
    }

    //处理post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    //处理get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");

       /* // 要重定向的新位置
        String site = new String("http://www.baidu.com");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);*/

        PrintWriter writer =response.getWriter();
        //加载MySQL驱动
        try {
            Class.forName(driverName);
            //与数据库建立连接，?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8，更改数据库的编码格式，加入时区
            conn = DriverManager.getConnection(url,USER,PASS);
            //初始化sql语句对象
            state = conn.createStatement();
            String sql;
            sql="select * from user";
            ResultSet rs = state.executeQuery(sql);

            userList =new ArrayList<>();
            userList.clear();

            while (rs.next()){
                String account = rs.getString("account");
                String password = rs.getString("password");
                User user =new User(account,password);
                userList.add(user);
            }
            writer.println("<script language='javascript'>alert('登陆成功')</script>");
            request.setAttribute("message",userList);
            request.getRequestDispatcher("home.jsp").forward(request,response);


            // 完成后关闭
            rs.close();
            state.close();
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            // 处理 Class.forName 和 JDBC 错误
            e.printStackTrace();
        }finally {
            // 最后是用于关闭资源的块
            try{
                if(state!=null)
                    state.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }

    }
}
