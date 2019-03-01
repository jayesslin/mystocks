package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Allstocks extends AppCompatActivity {

    private ListView list_stockview;
    private Context mContext;
    private List<stocklist> stock_list=new ArrayList<>();
    private stockAdapter mAdapter=null;
    private Handler handler;
    private ImageView imageView8,imageView10,search_bt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allstocks);
        list_stockview=(ListView)findViewById(R.id.stockslist);
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



        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    /**/

                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/

                    mAdapter=new stockAdapter(stock_list,mContext);
                    list_stockview.setAdapter(mAdapter);
                    list_stockview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            stocklist a=stock_list.get(position);
                            Intent intent=new Intent(Allstocks.this, stock_detail.class);
                            intent.putExtra("gid",a.getname());
                            startActivity(intent);

                        }
                    });





                }
            }

        };



       /* String[] strs={"1","2","3","4"};

        ArrayAdapter<String> stockadapter=new ArrayAdapter<>(this,R.layout.stock_item,R.id.stocktitle,strs);
        list_stock.setAdapter(stockadapter);*/

    }



    private void get(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    String  url_head="https://cloud.iexapis.com/beta/stock/";
                    String url_nil ="/quote?token=pk_d58ac872888a44d0915cce73e9849e32";
                    String[] quote={
                      "AAPL","AAAE","AMCG","CJJD","AKRK","AMGY","BEER",
                    };
                    for(int i=0;i<quote.length;i++){
                        String url_request=url_head+quote[i]+url_nil;
                        String result=sp.getStocklist();

                        JSONArray  array1=new JSONArray(result);
                        JSONObject a = array1.getJSONObject(i);

                        String gid = a.getString("symbol");
                        String openpri = a.getString("open");
                        String lastestpri = a.getString("latestPrice");
                        String uppic = a.getString("high");
                        String limit = a.getString("change");

                        stocklist stock = new stocklist(gid, openpri, lastestpri, limit);


                        stock_list.add(stock);
                    }
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




    }
