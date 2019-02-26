package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allstocks);
        list_stockview=(ListView)findViewById(R.id.stockslist);
        mContext=Allstocks.this;
        get();
        TextView main_home = (TextView)findViewById(R.id.main_home);
        main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),homePage.class);
                startActivity(ss11);
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

                    String result=sp.getRequest5();

                    JSONObject object1=new JSONObject(result);
                    JSONObject object2=object1.getJSONObject("result");
                    JSONArray array1=object2.getJSONArray("data");

                    for(int i=0;i<20;i++){
                        JSONObject a=array1.getJSONObject(i);
                        String name=a.getString("cname");
                        String gid =a.getString("symbol");
                        String openpri =a.getString("open");
                        String lastestpri =a.getString("price");
                        String uppic =a.getString("high");
                        String limit =a.getString("chg");

                        stocklist stock=new stocklist(gid,openpri,lastestpri,limit);


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
