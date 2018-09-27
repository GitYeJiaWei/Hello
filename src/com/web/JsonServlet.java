package com.web;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;

public class JsonServlet extends HttpServlet {
    public JsonServlet(){
        super();
    }

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("初始化。。。");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        HashMap<String,String> map = new HashMap<>();
        map.put("key","123456");
        map.put("code","tttetet");
        Gson gson = new Gson();
        String value = gson.toJson(map);

        User user = new User(name,password);
        try {
            if (user.isExist()){
                System.out.println("登陆成功");
                PrintWriter writer = response.getWriter();
                writer.write(value);
                //flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满
                //所以如果在用流的时候,没有用flush()这个方法,很多情况下会出现流的另一边读不到数据的问题
                writer.flush();
                writer.close();
            }else{
                System.out.println("登陆失败");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
