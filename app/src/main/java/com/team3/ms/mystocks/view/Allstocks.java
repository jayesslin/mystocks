package com.team3.ms.mystocks.view;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Allstocks extends AppCompatActivity {

    private ListView list_stockview;
    private Context mContext;
    private List<stocklist> stock_list=new ArrayList<>();
    private stockAdapter mAdapter=null;
    private Handler handler;
    private ImageView imageView8,imageView10,search_bt1,tongzhi2;
    private NavigationView navi_v;
    boolean flag=true,flag2=true;
    public Timer timer =new Timer();
    public int i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allstocks);
        list_stockview=(ListView)findViewById(R.id.stockslist);
        Intent s1 =getIntent();
        final String userName = s1.getStringExtra("id");//获得用户名
        mContext=Allstocks.this;
        get();
        TextView main_home = (TextView)findViewById(R.id.main_home1);
        main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),homePage.class);
                startActivity(ss11);
                finish();
            }
        });

        //logo 跳转
        //stock logo 跳转
        imageView10 = (ImageView)findViewById(R.id.stockicon);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(ss11);
                finish();
            }
        });
        //home logo 跳转
        imageView8 = (ImageView)findViewById(R.id.homeimg);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),homePage.class);
                startActivity(ss11);
                finish();
            }
        });
        search_bt1 = (ImageView)findViewById(R.id.search_bt1);
        search_bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss11 = new Intent(getApplicationContext(),Inquery.class);
                startActivity(sss11);

            }
        });
        tongzhi2=(ImageView) findViewById(R.id.tongzhi2);




        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    /**/
                    tongzhi2.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {
                            Log.i("timer",timer.toString());


                            if (flag2){

                                tongzhi2.setImageResource(R.drawable.tongzhi);
                                timer =new Timer();


                                timer.schedule(new TimerTask() {

                                    @Override
                                    public void run() {

                                        if(flag){
                                            add(stock_list);
                                            i++;

                                        }
                                        else if (!flag){
                                            loss(stock_list);
                                            i++;
                                        }
                                        flag=!flag;



                                    }
                                }, 5000,5000);
                                new Thread() {
                                    public void run() {
                                        Looper.prepare();
                                        Toast toast=Toast.makeText(getApplicationContext(), "Notify every 5s", Toast.LENGTH_SHORT);
                                        toast.show();
                                        Looper.loop();//这种情况下，Runnable对象是运行在子线程中的，可以进行联网操作，但是不能更新UI
                                    }
                                }.start();
                                flag2=!flag2;


                            }
                            else{
                                tongzhi2.setImageResource(R.drawable.tongzhi2);
                                timer.cancel();
                                flag2=!flag2;
                                new Thread() {
                                    public void run() {
                                        Looper.prepare();
                                        Toast toast=Toast.makeText(getApplicationContext(), "Notify canceled", Toast.LENGTH_SHORT);
                                        toast.show();
                                        Looper.loop();//这种情况下，Runnable对象是运行在子线程中的，可以进行联网操作，但是不能更新UI
                                    }
                                }.start();



                            }



                        }
                    });

                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/

                    mAdapter=new stockAdapter(stock_list,mContext);
                    list_stockview.setAdapter(mAdapter);
                    list_stockview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            stocklist a=stock_list.get(position);
                            Intent intent=new Intent(Allstocks.this, stock_detail_new.class);
                            intent.putExtra("gid",a.getGid());
                            startActivity(intent);

                        }
                    });





                }
            }

        };
        navi_v = findViewById(R.id.nav_view1);
        navi_v.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                int id = menuItem.getItemId();
                if(id == R.id.item1)
                {
                    Intent intent = new Intent();
                    intent.setClass(Allstocks.this,mystock.class);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.item2)
                {
                    Intent intent = new Intent();
                    intent.setClass(Allstocks.this,TransactionRecord.class);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.item3){
                    Intent intent = new Intent();
                    intent.setClass(Allstocks.this,personalPage.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);

                    return true;
                }else if(id == R.id.item4){
                    Intent intent = new Intent();
                    intent.setClass(Allstocks.this, dailyIncAndLos.class);
                    return true;
                }
                return false;
            }
        });



       /* String[] strs={"1","2","3","4"};

        ArrayAdapter<String> stockadapter=new ArrayAdapter<>(this,R.layout.stock_item,R.id.stocktitle,strs);
        list_stock.setAdapter(stockadapter);*/

    }



    private void get(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    /*String  url_head="https://cloud.iexapis.com/beta/stock/";
                    String url_nil ="/quote?token=pk_d58ac872888a44d0915cce73e9849e32";
                    String[] quote={
                      "AAPL","AAAE","AMCG","CJJD","AKRK","AMGY","BEER",
                    };
                    for(int i=1;i<quote.length;i++){
                        String url_request=url_head+quote[i]+url_nil;
                        String result=sp.getStocklist(url_request);

                        JSONArray  array1=new JSONArray(result);
                        JSONObject a = array1.getJSONObject(i);

                        String gid = a.getString("symbol");
                        String openpri = a.getString("open");
                        String lastestpri = a.getString("latestPrice");
                        String uppic = a.getString("high");
                        String limit = a.getString("change");

                        stocklist stock = new stocklist(gid, openpri, lastestpri, limit);


                        stock_list.add(stock);
                    }*/
                    String result=sp.getStocklist();

                    JSONArray  array1=new JSONArray(result);
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
                   /* String result=sp.getRequest5(1);

                    JSONObject object1=new JSONObject(result);
                    JSONObject object2=object1.getJSONObject("result");
                    JSONArray array1=object2.getJSONArray("data");

                    for(int i=0;i<20;i++) {
                        JSONObject a = array1.getJSONObject(i);
                        switch (a.getString("symbol")) {
                            case "BRK.A":
                                continue;
                            case "BRK.B":
                                continue;
                            default:
                                String name = a.getString("cname");
                                String gid = a.getString("symbol");
                                String openpri = a.getString("open");
                                String lastestpri = a.getString("price");
                                String uppic = a.getString("high");
                                String limit = a.getString("chg");

                                stocklist stock = new stocklist(gid, openpri, lastestpri, limit);


                                stock_list.add(stock);


                        }
                    }*/
                        /*String name=a.getString("cname");
                        String gid =a.getString("symbol");
                        String openpri =a.getString("open");
                        String lastestpri =a.getString("price");
                        String uppic =a.getString("high");
                        String limit =a.getString("chg");

                        stocklist stock=new stocklist(gid,openpri,lastestpri,limit);


                        stock_list.add(stock);
*/






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

    private void add(List<stocklist> sym_list){


        for(int i=0;i<sym_list.size();i++){
            Log.i("stock",sym_list.get(i).getLimit());
            float num=Float.parseFloat(sym_list.get(i).getLimit());
            if (num>1){
                Log.i("stock",""+num);
                notify1(sym_list.get(i).getGid(),Allstocks.this);
            }
        }


    }
    private void loss(List<stocklist> sym_list){


        for(int i=0;i<sym_list.size();i++){
            Log.i("stock",sym_list.get(i).getLimit());
            float num=Float.parseFloat(sym_list.get(i).getLimit());
            if (num<-1){
                Log.i("stock",""+num);
                notify1(sym_list.get(i).getGid(),Allstocks.this);
            }
        }


    }
    public void notify1(String number,Context context){
        Intent acIntent = new Intent(context, mystock.class);
        NotificationChannel channel = new NotificationChannel("1",
                "Channel1", NotificationManager.IMPORTANCE_DEFAULT);
        channel.enableLights(true); //是否在桌面icon右上角展示小红点
        channel.setLightColor(Color.GREEN); //小红点颜色
        channel.setShowBadge(true); //是否在久按桌面图标时显示此渠道的通知


        NotificationManager nm = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.createNotificationChannel(channel);
        PendingIntent pi = PendingIntent.getActivity(this, 0, acIntent, 0);
        Notification notify = new NotificationCompat.Builder(this,"1")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("TickerText:" + "You have new message!")
                .setContentTitle("stockid:"+number)
                .setContentText("The stock "+number+"‘s limit has changed")
                .setContentIntent(pi).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        nm.notify(1, notify);
    }




    }
