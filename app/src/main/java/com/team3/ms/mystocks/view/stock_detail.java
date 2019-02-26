package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.entity.stockdetail;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class stock_detail extends AppCompatActivity {
    private String gid;
    private List<stockdetail> stock_detaillist=new ArrayList<>();
    private Handler handler;










    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockdetail);
        Button backButton = (Button)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s2 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(s2);
            }
        });
        gid=getIntent().getStringExtra("gid");



        getgid(gid);

        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    TextView txt_name = (TextView) findViewById(R.id.name1);
                    TextView txt_symbol1 = (TextView) findViewById(R.id.symbol1);
                    TextView txt_laststpri = (TextView) findViewById(R.id.laststpri);
                    TextView txt_maxpri = (TextView) findViewById(R.id.maxpri);
                    TextView txt_minpri = (TextView) findViewById(R.id.minpri);
                    TextView txt_limit = (TextView) findViewById(R.id.limit);
                    TextView txt_traAmount = (TextView) findViewById(R.id.traAmount);
                    TextView txt_EPS = (TextView) findViewById(R.id.EPS);
                    TextView txt_divident = (TextView) findViewById(R.id.divident);
                    TextView txt_afterpic = (TextView) findViewById(R.id.afterpic);
                    TextView txt_afterlimit = (TextView) findViewById(R.id.afterlimit);
                    TextView txt_ustime = (TextView) findViewById(R.id.ustime);
                    Log.i("bb",stock_detaillist.get(0).getname());


                    txt_name.setText(stock_detaillist.get(0).getname());
                    txt_symbol1.setText(stock_detaillist.get(0).getgid());
                    txt_laststpri.setText(stock_detaillist.get(0).getlastestpri());
                    txt_maxpri.setText(stock_detaillist.get(0).getmaxpri());
                    txt_minpri.setText(stock_detaillist.get(0).getminpri());
                    if(stock_detaillist.get(0).getColor().equals("red")){
                        txt_limit.setTextColor(android.graphics.Color.RED);
                        txt_divident.setTextColor(android.graphics.Color.RED);
                    }else{
                        txt_limit.setTextColor(Color.GREEN);
                        txt_divident.setTextColor(Color.GREEN);
                    }
                    txt_limit.setText(stock_detaillist.get(0).getlimit());
                    txt_traAmount.setText(stock_detaillist.get(0).gettraAmount());
                    txt_EPS.setText(stock_detaillist.get(0).getEPS());

                    txt_divident.setText(stock_detaillist.get(0).getUppic());
                    txt_afterpic.setText(stock_detaillist.get(0).getafterpic());
                    txt_afterlimit.setText(stock_detaillist.get(0).getafterlimit());
                    txt_ustime.setText(stock_detaillist.get(0).getustime());



                }

            }

        };










    }

    private void getgid(final String gid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();

                    String result=sp.getRequest(gid);

                    JSONObject object1=new JSONObject(result);
                    JSONArray array1=object1.getJSONArray("result");
                    JSONObject object2=array1.getJSONObject(0);


                    JSONObject object3=object2.getJSONObject("data");



                    String name=object3.getString("name");

                    String gid= object3.getString("gid");
                            String lastestpri= object3.getString("lastestpri");
                            String maxpri=object3.getString("maxpri");
                            String minpri=object3.getString("minpri");
                            String limit=object3.getString("limit");
                            String traAmount=object3.getString("traAmount");
                            String EPS=object3.getString("EPS");
                            //更改， 股息改为涨跌额
                            String uppic=object3.getString("uppic");
                            String afterpic=object3.getString("afterpic");
                            String afterlimt=object3.getString("afterlimit");
                            String ustime=object3.getString("ustime");

                            stockdetail stock = new stockdetail(name,gid,lastestpri,maxpri,minpri,limit,traAmount,EPS,uppic,afterpic,afterlimt,ustime);

                    stock_detaillist.add(stock);


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

}
