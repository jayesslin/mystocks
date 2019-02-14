package com.team3.ms.mystocks.controller;
import com.team3.ms.mystocks.DBmgr.dbmanage;
import android.database.Cursor;
import android.util.Log;

import com.team3.ms.mystocks.entity.user;
public class usercontroller {
    public boolean verifes(String username,String password, dbmanage db){
        db.open();
        Cursor c = db.getUserL(username);
        String passwordtmp;
        while (c.moveToNext()) {
            passwordtmp=c.getString(c.getColumnIndex("password"));
            System.out.println(passwordtmp);
            if(password.equals(passwordtmp)){
                return true;
            }else {
                return false;
            }
        }
        db.close();
        return false;
    }
   /* public void registered(dbmanage dbMgr, String Email, String UserName, String password){
        user user = new user(Email,UserName,password);

        dbMgr.save(user);
    }*/
    public boolean registered(dbmanage dbMgr, String Email, String UserName, String password){
        user user = new user(Email,UserName,password);
        if(dbMgr.getUser(UserName)!=null){
            return false;
        }else{
            dbMgr.save(user);
            return true;
        }
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

  /* public Boolean ChangePassword(dbmanage dbMgr,String username,String email,String newpassword){
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
    }*/
    public int ChangePassword(dbmanage dbMgr,String username,String email,String newpassword){
        user user;
        Boolean msg=Boolean.TRUE;

        user=dbMgr.getUser(username);
        if(user!=null) {
            Log.i("*******", "old email:" + user.getEmail() + "  new email:" + email);
            String temp = user.getEmail();
            if (temp.equals(email)) {
                user.setPassword(newpassword);
                dbMgr.updateuser(user);
                Log.i("***********", " 调用db得update（）方法成功");
                dbMgr.close();
                return 1;
            } else {
                //not match
                dbMgr.close();
                return 2;
            }
        }
        else{
            //user is not exist
            dbMgr.close();
            return 3;
        }


    }

}
