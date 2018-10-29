package com.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DBDao{

    @Override
    public Connection getConn() {
        //重写父类的方法，如果想直接用父类的方法super.getConn()
        return super.getConn();
    }

    //获取用户列表
    public List<User> getUser(){
        List<User>userList = new ArrayList<User>();
        User user = null;
        String sql = "SELECT * FROM user";
        try{
            ResultSet rs = this.executeQuery(sql,null);
            while(rs.next()){
                user = new User(rs.getString("account"),rs.getString("password"));
                userList.add(user);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            this.closeAll();
        }
        return userList;
    }
    //根据用户名获取用户
    public User getUserByName(String name){
        User user = null;
        String sql = "SELECT * FROM user WHERE　account = ?";
        try{
            ResultSet rs = this.executeQuery(sql, new String[]{name});
            if(rs.next()){
                user = new User(rs.getString("account"),rs.getString("password"));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            this.closeAll();
        }
        return user;
    }
    //修改用户信息
    public boolean editUser(User user){
        boolean r = false;
        String sql = "UPDATE user SET password = ? WHERE account = ?";
        try{
            int num = this.executeUpdate(sql, new String[]{user.getPassword(),user.getAccount()});
            if(num > 0){
                r = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.closeAll();
        }
        return r;

    }
    //查询用户
    public boolean isExist(User user){
        boolean r =false;
        String sql ="select  * from user where account= ? and password= ?";

        try {
            ResultSet num = this.executeQuery(sql,new String[]{user.getAccount(),user.getPassword()});
            r = num.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return r;
    }

    //添加用户
    public boolean addUser(User user){
        boolean r = false;
        String sql = "insert into user(account,password)VALUES(?,?) ";
        try{
            int num = this.executeUpdate(sql,new String[]{user.getAccount(),user.getPassword()});
            if(num > 0){
                r = true;
            }
        }catch(Exception e){
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }finally{
            this.closeAll();
        }
        return r;
    }
    //删除指定用户
    public boolean delUser(String name){
        boolean r = false;
        String sql = "DELETE FROM user WHERE account = ?";
        try{
            int num = this.executeUpdate(sql,new String[]{name});
            if(num > 0){
                r = true;
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            this.closeAll();
        }
        return r;
    }
}
