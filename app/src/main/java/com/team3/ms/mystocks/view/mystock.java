package com.team3.ms.mystocks.view;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.controller.collectcontroller;
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

public class mystock extends AppCompatActivity {
    private ListView collect_list;
    private ImageView backButton,tongzhi,qtongzhi;
    private Context resContext;
    private Handler handler;
    public List<stocklist> stock_list=new ArrayList<>();
    private stockAdapter mAdapter=null;
    private dbmanage dbMgr;
    public static final String TAG = "MyService";
    private final static String ACTION_NOTIFICATION = "ACTION_NOTIFICATION";
    boolean flag=true,flag2=true;
    Timer timer =new Timer();

    public int i=0;
    public String timerstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mystock);
        dbMgr= new dbmanage(mystock.this,"MyStocks.db",null,1);
        collect_list = (ListView)findViewById(R.id.collect_list);
        Intent s1 =getIntent();
        final String userName = s1.getStringExtra("id");
        backButton = (ImageView) findViewById(R.id.backH);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent s1 = new Intent(getApplicationContext(),Allstocks.class);
                s1.putExtra("id",userName);
                startActivity(s1);
            }
        });
        collectcontroller cc = new collectcontroller();
        String[] list = cc.getlist(dbMgr);
        resContext = mystock.this;
        tongzhi=(ImageView) findViewById(R.id.tongzhi);
        get_list(list);


        handler =new Handler(){
            public void handleMessage(Message msg){
                System.out.println(msg.what);
                if(msg.what == 1){
                    /**/
                    tongzhi.setOnClickListener(new View.OnClickListener() {
                        @Override

                        public void onClick(View v) {




                            if (flag2){

                                tongzhi.setImageResource(R.drawable.tongzhi);
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
                                tongzhi.setImageResource(R.drawable.tongzhi2);
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
                    mAdapter=new stockAdapter(stock_list,resContext);
                    collect_list.setAdapter(mAdapter);
                    collect_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            stocklist a=stock_list.get(position);
                            System.out.println("this"+a);
                            Intent intent=new Intent(mystock.this, stock_detail_new.class);
                            System.out.println(a.getname());
                            intent.putExtra("gid",a.getGid());
                            startActivity(intent);

                        }
                    });

                }
            }

        };


    }
    public void showToast(){
        Toast toast=Toast.makeText(getApplicationContext(), "notify", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void get_list(final String[] sym_list){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    String try0 = sp.getStock("LEE");

                    String result=sp.getRequest5(1);

                    JSONObject object1=new JSONObject(result);
                    JSONObject object2=object1.getJSONObject("result");
                    JSONArray array1=object2.getJSONArray("data");
                    int i = 0;
                    int j = 0;
                    System.out.println("the collect list is"+sym_list.length);
                    while(i < 20 && j < sym_list.length){
                        JSONObject a=array1.getJSONObject(i);
                        String gid =a.getString("symbol");
                        System.out.println("now is " + gid + "i" + i);
                        for (int z = 0; z < sym_list.length; z++){
                            System.out.println("list is  " + sym_list[z]);
                            if(sym_list[z].equals(gid)){
                                System.out.println("the right" + sym_list[z]);
                                String name=a.getString("cname");
                                String openpri =a.getString("open");
                                String lastestpri =a.getString("price");
                                String uppic =a.getString("high");
                                String limit =a.getString("chg");
                                stocklist stock=new stocklist(gid,openpri,lastestpri,limit);
                                stock_list.add(stock);
                                j ++;
                                break;
                            }
                        }
                        i++;
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
        }).start();
    }
    private void add(List<stocklist> sym_list){


        for(int i=0;i<sym_list.size();i++){
        Log.i("stock",sym_list.get(i).getLimit());
        float num=Float.parseFloat(sym_list.get(i).getLimit());
        if (num>0){
            Log.i("stock",""+num);
            notify1(sym_list.get(i).getGid(),mystock.this);
        }
        }


    }
    private void loss(List<stocklist> sym_list){


        for(int i=0;i<sym_list.size();i++){
            Log.i("stock",sym_list.get(i).getLimit());
            float num=Float.parseFloat(sym_list.get(i).getLimit());
            if (num<0){
                Log.i("stock",""+num);
                notify1(sym_list.get(i).getGid(),mystock.this);
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
                .setContentText("The stock "+number+"limit has changed")
                .setContentIntent(pi).build();
        notify.flags |= Notification.FLAG_AUTO_CANCEL; // FLAG_AUTO_CANCEL表明当通知被用户点击时，通知将被清除。
        nm.notify(1, notify);
    }




}

