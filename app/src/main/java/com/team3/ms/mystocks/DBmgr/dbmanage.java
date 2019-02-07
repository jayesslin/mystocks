package com.team3.ms.mystocks.DBmgr;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbmanage extends SQLiteOpenHelper {
    //database name
    private static final String DB_NAME = "MyStocks.db";
    //table name
    private static final String TBL_NAME = "user";
    //建立数据库表SQL语句
    private static final String CREATE_TBL = "create table user " +
            "(id integer primary key autoincrement,name varchar(20) unique,email varchar(20),password varchar(20))";

    //SQLiteDatabase
    private SQLiteDatabase db;

    public dbmanage(Context context) {
        super(context, DB_NAME, null, 1);
    }

    public dbmanage(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(CREATE_TBL_event);
        db.execSQL(CREATE_TBL);*/
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Cursor getUser(String name){
        Cursor c = db.rawQuery("select * from user where name='"+name+"'", null);
        //Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
        return c;
    }
}

