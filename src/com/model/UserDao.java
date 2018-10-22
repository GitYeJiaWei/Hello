package com.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends DBDao{
    //获取用户列表
    public List<User> getUser(){
        List<User>userList = new ArrayList<User>();
        User user = null;
        String sql = "SELECT * FROM tb_user";
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
        String sql = "SELECT * FROM tb_user WHERE　username = ?";
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
        String sql = "UPDATE tb_user SET password = ? WHERE account = ?";
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
    //添加用户
    public boolean addUser(User user){
        boolean r = false;
        String sql = "INSERT INTO tb_user(account,password)VALUES(?,?) ";
        try{
            int num = this.executeUpdate(sql,new String[]{user.getAccount(),user.getPassword()});
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
    //删除指定用户
    public boolean delUser(String name){
        boolean r = false;
        String sql = "DELETE FROM tb_user WHERE username = ?";
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
