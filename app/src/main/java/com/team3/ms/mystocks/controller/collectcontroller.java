package com.team3.ms.mystocks.controller;

import android.database.Cursor;

import com.team3.ms.mystocks.DBmgr.dbmanage;

public class collectcontroller {
    public boolean collect_stock(dbmanage dbMgr, String symbol){
        if(dbMgr.vertify_symbol(symbol) != null){
            delete_collect(dbMgr, symbol);
            String[] ver = dbMgr.getcollect();
            System.out.println("len1"+ver.length);
            return false;
        }else{
            dbMgr.save_collect(symbol);
            String[] ver = dbMgr.getcollect();
            System.out.println("len2"+ver.length);
            return true;
        }
    }
    public String[] getlist(dbmanage dbMgr){
        String[] res_list =  dbMgr.getcollect();
        return res_list;
    }
    public void delete_collect(dbmanage dbMgr, String symbol){
        dbMgr.removestock(symbol);
    }

}
