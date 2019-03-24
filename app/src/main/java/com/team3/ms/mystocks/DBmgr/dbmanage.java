package com.team3.ms.mystocks.DBmgr;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.team3.ms.mystocks.entity.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class dbmanage extends SQLiteOpenHelper {
    //private Context context;
    //database name
    private static final String DB_NAME = "MyStocks.db";
    //table name
    private static final String TBL_NAME = "user";
    private static final String TBL_NAME_collect = "collect";
    //建立数据库表SQL语句
    private static final String CREATE_TBL = "create table user " +
            "(id integer primary key autoincrement,name varchar(20) unique,email varchar(20),password varchar(20))";
    private static final String CREATE_TBL_collect = "create table collect (id integer primary key autoincrement,symbol varchar(20))";

    //SQLiteDatabase
    private SQLiteDatabase db;

    public dbmanage(Context context) {

        super(context, DB_NAME, null, 4);
    }

    public dbmanage(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, 4);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*db.execSQL(CREATE_TBL_event);*/

        db.execSQL(CREATE_TBL_collect);
        db.execSQL(CREATE_TBL);
        Log.i("=======","creat!!!!!!!! ");


        //Toast.makeText(context, "创建数据库成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

//        if(oldVersion < 3){//当数据库版本小于版本2时，就要升级下面的所有字段
//
//            db.execSQL(CREATE_TBL);
//            db.execSQL(CREATE_TBL_collect);
//        }

    }
    public void open() {
        System.out.println("oooo");
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
        Log.i("=======","email " + user.getEmail()+" name "+user.getUserName()+" psd "+user.getPassword());
        close();
    }
    public void save_collect(String symbol){
        open();

        db.execSQL("insert into collect values(null,?)",new String[]{symbol});
        Cursor c = db.query("collect", new String[]{"id", "symbol"}, null, null, null, null, null);
        int num = c.getColumnCount();
        System.out.println("xffxff The collected symbol is :"+ symbol);
        System.out.println("xffxff The nnnnn is :"+ num);
        Log.i("=======","symbol " + symbol);
        close();
    }


   /* public Cursor getUser(String name){
        open();
        Cursor c = db.rawQuery("select * from user where name='"+name+"'", null);
        //Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
        close();
        return c;

    }*/
    public user getUser(String UserName){
        open();
        Cursor c = db.rawQuery("select * from user where name='"+UserName+"'", null);
        String username,email,password;
        user user;
        while (c.moveToNext()) {
            String id = c.getString(0);
            username = c.getString(1);
            email= c.getString(2);
            password = c.getString(3);
            user = new user(email,username,password);
            Log.i("++++++GetUSER","id: "+id+ " eamil: "+user.getEmail()+" password: "+password+" username: "+username);
            return user;
        }
        close();
       return null;
    }
    public String[] getcollect(){
        open();
//        Cursor result=db.rawQuery("select * from collect", null);
//        while(result.moveToNext()){
//            System.out.println("ddddd"+result.getString(1));
//        }
        Cursor c = db.query("collect", new String[]{"id", "symbol"}, null, null, null, null, null);
        int num = c.getCount();
        String[] sym_list = new String[num];
        if(c.getColumnCount()>0){

            int i = 0;
            while (c.moveToNext()) {
                sym_list[i] = c.getString(1);
                i++;
            }
            System.out.println(sym_list);

        }
        close();
        return sym_list;
    }
    public String vertify_symbol(String symbol) {
        open();
        Cursor c = db.rawQuery("select * from collect where symbol='" + symbol + "'", null);
        String symbol_collected;
        while (c.moveToNext()) {
            String id = c.getString(0);
            symbol_collected = c.getString(1);
            return symbol_collected;
        }
        close();
        return null;
    }
    public void removestock(String symbol) {
        open();
        int deleteCount = db.delete(TBL_NAME_collect,  "symbol=?", new String[] { symbol + "" });

        System.out.println("The delete is "+deleteCount);
        close();
    }
    public Cursor getUserL(String name){
        open();
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(name);
        Cursor c;
        if(m.matches()){
            c = db.rawQuery("select * from user where email='"+name+"'", null);
        }else{
            c = db.rawQuery("select * from user where name='"+name+"'", null);
        }

        System.out.println(c.getCount());
        //Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);
        close();
        return c;

    }










    public boolean updateuser(user user){
        open();
        String user_name= user.getUserName();
        String new_password=user.getPassword();
        ContentValues values =new ContentValues();
        values.put("password",new_password);
        db.update(TBL_NAME, values, "name=?", new String[]{""+user_name});
        Log.i("***********"," 修改数据库成功, 新密码："+new_password);
        close();
        return true;

    }






    /********************************************************************************************/
    /********************************************************************************************/
    /********************************              for test!         *****************************/
    /********************************************************************************************/
    public void query(){
        open();
        Cursor c = db.rawQuery("select * from user ", null);
        //Cursor c = db.query(TBL_NAME, null, null, null, null, null, null);

        while (c.moveToNext()) {
            String id = c.getString(0);
            String email= c.getString(2);
            String username = c.getString(1);
            String password = c.getString(3);
            user userTest = new user(email,username,password);
            Log.i("++++++","id: "+id+ " username: "+username+" eamil: "+email+" password: "+password);
        }
        close();
    }


}

