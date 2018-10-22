package com.model;

import java.sql.*;

public class DBHelper {
    private Connection conn;   //数据库连接对象
    private Statement state;    //sql语句执行对象

    private static final String driverName ="com.mysql.cj.jdbc.Driver";
    private static final String url ="jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String sDropDB ="drop database if exists userdb";
    private static final String sCreateDB ="create database userdb";
    private static final String sCreateDbifNot ="create database if not exists userdb";
    private static final String sUseDB ="use userdb";
    private static final String sDropTb ="drop table if exists user";
    private static final String sCreateTb ="create table user(account varchar(20),password varchar(20))";
    private static final String sAlter ="alter table user change account account varchar(50) character set utf8";
    private static final String sInsert1 ="insert into user values('叶','123')";
    private static final String sInsert2 ="insert into user values('admin','123')";

    public DBHelper(){
        try {
            //加载MySQL驱动
            Class.forName(driverName);
            //与数据库建立连接，?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8，更改数据库的编码格式，加入时区
            conn = DriverManager.getConnection(url,"root","123456");
            //初始化sql语句对象
            state = conn.createStatement();
            state.execute(sDropDB);    //如果数据库userdb已经存在先删除
            state.execute(sCreateDB);  //创建数据库userdb
            //state.execute(sCreateDbifNot);//如果不存在数据库userdb则创建
            state.execute(sUseDB);     //使用数据库userdb
            state.execute(sDropTb);    //如果user表已存在就先删除
            state.execute(sCreateTb);  //创建数据库user表
            state.execute(sAlter);     //更改表内的编码为utf-8
            state.execute(sInsert1);    //向user表插入一条记录
            state.execute(sInsert2);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    //查询某用户是否存在
    public Boolean Query(User user) throws SQLException{
        ResultSet rs = null;

        //查询是否有account =‘admin’，password =‘123’的记录
        rs = state.executeQuery("select  * from user where account='"+user.getAccount()+"'and password='"+user.getPassword()+"'");
        return rs.next();
    }

    //检测是否插入数据
    public boolean Insert(User user) throws SQLException{
        int rs;
        //如果采用execute方法的话，返回的是布尔值，并且因为返回结果集，
        //所以就返回false了，虽说数据库还是正常写入了的
        rs=state.executeUpdate("insert into user values ('"+user.getAccount()+"','"+user.getPassword()+"')");
        if (rs>0){
            return true;
        }{
            return false;
        }
    }


}
