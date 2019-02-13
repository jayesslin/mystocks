package com.team3.ms.mystocks.controller;
import com.team3.ms.mystocks.DBmgr.dbmanage;
import android.database.Cursor;
import android.util.Log;

import com.team3.ms.mystocks.entity.user;
public class usercontroller {
    public boolean login(String username,String password, dbmanage db){
        user c = db.getUser(username);
        if(password==c.getPassword()){
            return true;
        }else {
            return false;
        }
    }
    public void registered(dbmanage dbMgr, String Email, String UserName, String password){
        user user = new user(Email,UserName,password);
        dbMgr.save(user);
    }
    public boolean changepassword(dbmanage db, String email, String username, String Newpassword){
        Log.i("*******","查询用户");
        user c = db.getUser(username);
        Log.i("*******111s","用户名： "+c.getUserName()+" 密码： "+c.getPassword());
        if(email.equals(c.getEmail())){
            Log.i("*******","准备 插入");
            c.setPassword(Newpassword);
            db.updateuser(c);
            return true;
        }else {
            return false;
        }
    }

   public Boolean ChangePassword(dbmanage dbMgr,String username,String email,String newpassword){
        user user;
        Boolean msg=Boolean.TRUE;

        user=dbMgr.getUser(username);
        Log.i("*******","old email:"+user.getEmail()+"  new email:"+email);
        String temp= user.getEmail();
        if(temp.equals(email)){
            user.setPassword(newpassword);
            dbMgr.updateuser(user);
            Log.i("***********"," 调用db得update（）方法成功");
            return msg;
        }
        else {
            msg=false;
        }
        dbMgr.close();
        return msg;
    }

}
