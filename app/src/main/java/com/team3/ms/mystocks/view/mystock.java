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

public class mystock extends AppCompatActivity {
    private ListView collect_list;
    private ImageView backButton;
    private Context resContext;
    private Handler handler;
    private List<stocklist> stock_list=new ArrayList<>();
    private stockAdapter mAdapter=null;
    private dbmanage dbMgr;

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
        get_list(list);
        handler =new Handler(){
            public void handleMessage(Message msg){
                System.out.println(msg.what);
                if(msg.what == 1){
                    /**/
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
    private void get_list(final String[] sym_list){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    String try0 = sp.getStock("LEE");
                    System.out.println("now is "  + try0);
                    String result=sp.getRequest5(1);
                    System.out.println("now is "  + result);
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
}
