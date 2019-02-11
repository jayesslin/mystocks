package com.team3.ms.mystocks.controller;
import com.team3.ms.mystocks.DBmgr.dbmanage;
import android.database.Cursor;
import com.team3.ms.mystocks.entity.user;
public class usercontroller {
    public boolean login(String Username,String password, dbmanage db){
        Cursor c = db.getUser(Username);
        String passwordtmp;
        while (c.moveToNext()) {

            passwordtmp=c.getString(c.getColumnIndex("password"));
            if(password.equals(passwordtmp)){
                return true;
            }else {
                return false;
            }
        }

        return false;
    }
    public void registered(dbmanage dbMgr, String Email, String UserName, String password){
        user user = new user(Email,UserName,password);
        dbMgr.save(user);
    }
}
