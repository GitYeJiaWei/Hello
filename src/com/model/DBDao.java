package com.model;

import java.sql.*;

public class DBDao {
    Connection conn = null;       //数据库连接对象
    PreparedStatement ps = null;  //sql语句执行对象,Statement的子类
    Statement state =null;        //sql语句执行对象
    ResultSet rs = null;

    //打开连接
    public Connection getConn(){
        String driverName ="com.mysql.cj.jdbc.Driver";
        String url ="jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
        String sDropDB ="drop database if exists userdb";
        String sCreateDB ="create database userdb";
        String sCreateDBifNot ="create database if not exists userdb";
        String sUseDB ="use userdb";
        String sDropTb ="drop table if exists user";
        String sCreateTb ="create table user(account varchar(20),password varchar(20))";
        String sCreateTbifNot ="create table if not exists user(id integer not null auto_increment,account varchar(20) not null," +
                "password varchar(20) not null,primary key (id),unique(account))engine =innodb auto_increment =1 default charset =utf8;";
        String sAlter ="alter table user change account account varchar(20) character set utf8";
        String sInsert1 ="insert into user values('叶','123')";
        String sInsert2 ="insert into user values('admin','123')";

        try{
            Class.forName(driverName);
            conn = DriverManager.getConnection(url,"root","123456");
            state = conn.createStatement();
            state.execute(sCreateDBifNot);
            state.execute(sUseDB);
            state.execute(sCreateTbifNot);
            //state.execute(sAlter);     //更改表内的编码为utf-8
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    //关闭连接
    public void closeAll(){
        try{
            if(rs != null){
                rs.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try{
                if(ps != null){
                    ps.close();
                }
                if (state !=null){
                    state.close();
                }
            }catch(SQLException e){
                e.printStackTrace();
            }finally{
                try{
                    if(conn != null){
                        conn.close();
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        }

    }
    //执行sql语句，可以进行查询
    //executeQuery返回单个 ResultSet 对象
    public ResultSet executeQuery(String preparedSql,String []param){
        try{
            ps = conn.prepareStatement(preparedSql);
            //ps.execute(sql);
            if(param != null){
                for (int i = 0; i < param.length; i++) {
                    ps.setString(i + 1, param[i]);
                }
            }
            rs = ps.executeQuery();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return rs;

    }
    //执行sql语句，增加，修改，删除
    //executeUpdate该语句可能为 INSERT、UPDATE 或 DELETE 语句，返回int
    public int executeUpdate(String preparedSql,String[]param){
        int num = 0;
        try{
            ps = conn.prepareStatement(preparedSql);
            //ps.execute(sql);
            if(ps != null){
                for (int i = 0; i < param.length; i++) {
                    ps.setString(i + 1, param[i]);
                }
            }
            num = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return num;
    }
}
