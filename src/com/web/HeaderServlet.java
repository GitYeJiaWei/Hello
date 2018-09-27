package com.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HeaderServlet extends HttpServlet {
    public HeaderServlet(){
        super();
    }

    //处理post请求
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);

    }

    //处理get请求
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置错误代码和原因
        //response.sendError(404,"服务器无法找到所请求的页面");

        //每5秒自动刷新
        response.setIntHeader("Refresh",5);
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer =response.getWriter();
        Date date =new Date();
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String title = "自动刷新 Header";
        String docType =
                "<!DOCTYPE html>\n";
        writer.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n"+
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<p>当前时间是：" + format.format(date) + "</p>\n");
        writer.close();
    }

}
