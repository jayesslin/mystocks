package com.team3.ms.mystocks.controller;
import com.team3.ms.mystocks.DBmgr.dbmanage;
import android.database.Cursor;

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
        db.close();
        return false;
    }
}
