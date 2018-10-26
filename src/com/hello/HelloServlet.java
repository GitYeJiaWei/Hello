package com.hello;

import com.model.User;
import com.model.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
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
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String account = request.getParameter("account");
        String password = request.getParameter("password");
       /* request.getRemoteAddr();//获取IP地址
        // 为名字和密码创建 Cookie
        Cookie name = new Cookie("name",account);
        Cookie pass = new Cookie("pass",password);
        // 为两个 Cookie 设置过期日期为 24 小时后
        name.setMaxAge(60*2);
        pass.setMaxAge(60*2);
        // 在响应头中添加两个 Cookie
        response.addCookie(name);
        response.addCookie(pass);*/

        HttpSession session =request.getSession();
        String verificationCode = (String)session.getAttribute("helloCode");
        String checkcode = request.getParameter("checkcode");


        char[] chars1 =verificationCode.toCharArray();
        for (int i =0;i<chars1.length;i++){
            chars1[i] = isUpperCase(chars1[i]);
        }
        verificationCode = new String(chars1);

        char[] chars2 =checkcode.toCharArray();
        for (int i =0;i<chars2.length;i++){
            chars2[i] = isUpperCase(chars2[i]);
        }
        checkcode = new String(chars2);

        PrintWriter out = response.getWriter();
        if(checkcode.equals(verificationCode)){
            out.println(1);
            //创建用户
            User user = new User(account,password);
            UserDao userDao =new UserDao();
            userDao.getConn();
            if (userDao.isExist(user)){
                //登陆成功
                response.sendRedirect("/Welcome");
            }else{
                //登陆失败
                response.sendRedirect("/Error");
            }
        }else{
            out.println("验证码不正确");
        }
        out.flush();
        out.close();
    }

    //通过 ASCII 码判断字母大小写，ASCII在 65-90 之间是大写，97-122 是小写
    //通过 ASCII 加 32 转换为小写，减 32 转换为大写
    //或者通过CharCharacter.toLowerCase(c)转小写，Character.toUpperCase(c)转大写
    //转小写
    public char isUpperCase(char c){
        if (c>=65 && c<=90){
            c =Character.toLowerCase(c);
        }
        return c;
    }
    //转大写
    public char isLowerCase(char c){
        if (c>=97 && c<=122){
            c= Character.toUpperCase(c);
        }
            return c;

    }
}
