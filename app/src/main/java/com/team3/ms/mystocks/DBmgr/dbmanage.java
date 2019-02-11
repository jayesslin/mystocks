package com.team3.ms.mystocks.DBmgr;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.team3.ms.mystocks.entity.user;

public class dbmanage extends SQLiteOpenHelper {
    //private Context context;
    //database name
    private static final String DB_NAME = "MyStocks.db";
    //table name
   // private static final String TBL_NAME = "user";
    //建立数据库表SQL语句
    private static final String CREATE_TBL = "create table user " +
            "(id integer primary key autoincrement,name varchar(20) unique,email varchar(20),password varchar(20))";

    //SQLiteDatabase
    private SQLiteDatabase db;

    public dbmanage(Context context) {

        super(context, DB_NAME, null, 3);
    }

    public dbmanage(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, 3);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(CREATE_TBL_event);*/
        db.execSQL(CREATE_TBL);

        //Toast.makeText(context, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(oldVersion < 3){//当数据库版本小于版本2时，就要升级下面的所有字段

            db.execSQL(CREATE_TBL);
        }
    }
    public void open() {
        db = getWritableDatabase();
    }
    //close db
    public void close(){
        if (db != null){
            db.close();

        }
    }

    public void save(user user){
        open();
        db.execSQL("insert into user values(null,?,?,?)",new String []{user.getUserName(),user.getEmail(),user.getPassword()});
        Log.i("=======","email" + user.getEmail()+" name "+user.getUserName()+" psd "+user.getPassword());
        close();
    }
    public Cursor getUser(String name){
        open();
        Cursor c = db.rawQuery("select * from user where name='"+name+"'", null);
        //Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
        close();
        return c;

    }
}

