package com.team3.ms.mystocks.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;


import android.widget.TextView;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IncomeLossObject;
import com.team3.ms.mystocks.entity.IncomeandLoss;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.entity.user;
import com.team3.ms.mystocks.entity.user_info;
import com.team3.ms.mystocks.tools.Myprofile_tools;
import com.team3.ms.mystocks.tools.Stock_inlo_adapter;
import com.team3.ms.mystocks.tools.Stocks_provider;

import android.support.v4.widget.SwipeRefreshLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class personalPage extends AppCompatActivity {
    private TextView Name,stockrecord;
    private TextView EmailAdress,text21,text17,totalgain,totalasset;
    private ImageView exit,back;
    private dbmanage dbMgr;
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int loss,total;
     Handler handler;
    List<stocklist> stock_list=new ArrayList<>();
    private void exitapp() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        dbMgr = new dbmanage(personalPage.this, "MyStocks.db", null, 1);
        Name = (TextView) findViewById(R.id.Name);
        text21 = (TextView) findViewById(R.id.textView21);
        text17 = (TextView) findViewById(R.id.textView17);
        totalgain = (TextView) findViewById(R.id.textView16);
        totalasset = (TextView) findViewById(R.id.textView19);
        EmailAdress = (TextView) findViewById(R.id.EmailAdress);
        exit = (ImageView) findViewById(R.id.exit);
        back=(ImageView) findViewById(R.id.backpersonal);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        stockrecord=(TextView) findViewById(R.id.textView24);
        stockrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(personalPage.this,TransactionRecord.class);
                startActivity(intent);

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitapp();

            }
        });
        Intent s1 = getIntent();
        String userName = s1.getStringExtra("id");//获得用户名
        Name.setText(userName);


        //通过用户名获取用户对象
        //user user = dbMgr.getUser(userName);



        dbMgr= new dbmanage(personalPage.this,"MyStocks.db",null,1);
        Name = (TextView)findViewById(R.id.Name);
        EmailAdress = (TextView)findViewById(R.id.EmailAdress);

        //老方法 intent传值
 /*       Intent s1 =getIntent();
        String userName = s1.getStringExtra("id");//获得用户名*/
            /*  Name.setText(userName);
        //通过用户名获取用户对象
        user user = dbMgr.getUser(userName);*/

        //通过对象获取对象邮箱地址

        //单例模式
        user_info sd= user_info.getInstance();
        String username = sd.getId();
        Log.i("*************"," id 是"+ username);
        Name.setText(username);
        //通过用户名获取用户对象
        user user = dbMgr.getUser(username);
        String email_address = user.getEmail();
        Log.i("*************"," email 是"+ email_address);
        EmailAdress.setText(email_address);

        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe_ly);//初始化下拉刷新控件，
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               get();
                mSwipeRefreshLayout.setRefreshing(false);//刷新完成          
                  }  });
        /*Myprofile_tools mt= new Myprofile_tools();
        mt.get(handler,stock_list).start();*/
        get();
        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    int i = stock_list.size();
                    Log.i("++++++++","list大小是" + i);
                    System.out.print("+++++++++++++listdaixaoshi : "+i);
                    IncomeandLoss il = new IncomeandLoss();
                    double total_origin = il.getTotal_origin();
                    double total;
                    double BABA = Double.parseDouble(stock_list.get(0).getLastestpri());
                    double BABA_open = Double.parseDouble(stock_list.get(0).getOpenpri());
                    BABA*=100;
                    BABA_open*=100;
                    double PEP = Double.parseDouble(stock_list.get(1).getLastestpri());
                    double PEP_open = Double.parseDouble(stock_list.get(1).getOpenpri());
                    PEP*=200;
                    PEP_open*=200;
                    double AAPL = Double.parseDouble(stock_list.get(2).getLastestpri());
                    double AAPL_open = Double.parseDouble(stock_list.get(2).getOpenpri());
                    AAPL*=50;
                    AAPL_open*=50;
                    double AMZN = Double.parseDouble(stock_list.get(3).getLastestpri());
                    double AMZN_open = Double.parseDouble(stock_list.get(3).getOpenpri());
                    AMZN*=30;
                    AMZN_open*=30;
                    double FB = Double.parseDouble(stock_list.get(4).getLastestpri());
                    double FB_open = Double.parseDouble(stock_list.get(4).getOpenpri());
                    FB*=20;
                    FB_open*=20;
                    total = total_origin-(BABA+PEP+AAPL+AMZN+FB);
                    double daily = (BABA_open+PEP_open+AAPL_open+AMZN_open+FB_open)-(BABA+PEP+AAPL+AMZN+FB);
                    Log.i("++++++++","totalgain大小是" + total);

                    BigDecimal a   =   new   BigDecimal(total);
                    double   f2   =   a.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    String res = f2+"$";
                    totalgain.setText(res);
                    //daily_iandL.setText(res_daily);
                    /**/
                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/
                    /* mAdapter=new stockAdapter(stock_list,mContext);*/
                    /*mAdapter=new Stock_inlo_adapter(stock_list, dailyIncAndLos.this);
                    listView1.setAdapter( mAdapter);
                    listView2.setAdapter( mAdapter);*/
                    double profit1 =0.00;
                    double profit2 =0.00;
                    double[] buy_price = il.getStock_buy_price();
                    for(int j =0 ; j <5;j++){
                        Log.i("++++++++ss++",""+Double.parseDouble(stock_list.get(j).getLastestpri()));
                        if(Double.parseDouble(stock_list.get(j).getLastestpri())>=Double.parseDouble(stock_list.get(j).getOpenpri())||stock_list.get(j).getGid().equals("BABA")){
                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
                            profit1 += temp_profit;
                        }
                        else if (Double.parseDouble(stock_list.get(j).getLastestpri())<Double.parseDouble(stock_list.get(j).getOpenpri())){
                            //String temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit())+"";
                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
                            profit2 += temp_profit;
                        }
                    }
                    double daily_profit = profit1+ profit2;
                    BigDecimal temp_dailly   =   new   BigDecimal(daily_profit);
                    double   profit   =   temp_dailly.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    String s = profit+"$";
                    text17.setText(s);
                    totalasset.setText(122159.30+f2+"");
                    double ratio  = f2/122159.30;
                    BigDecimal c   =   new   BigDecimal(ratio);
                    double   f3   =   c.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    text21.setText(f3+"%");
                    if(f2<0){
                        totalgain.setTextColor(android.graphics.Color.RED);
                        totalasset.setTextColor(android.graphics.Color.RED);
                        text21.setTextColor(android.graphics.Color.RED);
                    }else{
                        totalgain.setTextColor(android.graphics.Color.GREEN);
                        totalasset.setTextColor(android.graphics.Color.GREEN);
                        text21.setTextColor(android.graphics.Color.GREEN);
                    }
                    if(profit<0){
                        text17.setTextColor(android.graphics.Color.RED);

                    }else{
                        text17.setTextColor(android.graphics.Color.GREEN);

                    }

                }
            }

        };


    }
    private void get(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    String result=sp.getStocklist();
                    JSONArray array1=new JSONArray(result);
                    for(int i=0;i<10;i++) {
                        JSONObject a = array1.getJSONObject(i);

                        String gid = a.getString("symbol");
                        String openpri = a.getString("open");
                        String lastestpri = a.getString("latestPrice");
                        String uppic = a.getString("high");
                        String limit = a.getString("change");
                        stocklist stock = new stocklist(gid, openpri, lastestpri, limit);
                        stock_list.add(stock);
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);


                }
                catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();}

}
