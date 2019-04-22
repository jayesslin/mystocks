package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.funds;
import com.team3.ms.mystocks.entity.fundslist;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.fundsadapter;
import com.team3.ms.mystocks.tools.stockAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class myfunds_search extends AppCompatActivity {
    private ListView searchList;
    private fundsadapter mAdapter=null;
    private ListView fund_view;
    private Button goButton;
    private Button backButton;
    private EditText searchText;

    private Context resContext;
    private Handler handler;
    private List<funds> funds_list=new ArrayList<>();
    private List<funds> resfund_list=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfund_search_page);
        searchText = (EditText)findViewById(R.id.symbol_search);
        goButton = (Button)findViewById(R.id.search_fund);
        backButton = (Button)findViewById(R.id.back_myfund_search);
        fund_view = (ListView) findViewById(R.id.funds_list_search);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symbol = searchText.getText().toString();
                get_funds(symbol);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),myfunds.class);
                startActivity(s1);
            }
        });



        handler =new Handler(){
            public void handleMessage(Message msg) {
                System.out.println(msg.what);
                if (msg.what == 1) {
                    /**/
                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/
                    mAdapter = new fundsadapter(resfund_list, myfunds_search.this);
                    fund_view.setAdapter(mAdapter);
                } else {
                    System.out.println("No");
                    Toast.makeText(myfunds_search.this, "The stock you are looking for does not exist!!!", (int) 2000).show();
                }

            }
        };


    }

    private void get_funds(final String symbol){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Log.i("++++++++++++++搜索匹配 入口","搜索的是："+symbol);
                    fundslist fl = new fundslist();
                    List<funds> fundlist = fl.getFund_list();
                    int i = 0;
                    boolean flag = false;
                    while(flag == false && i < 2){
                        Log.i("++++++++++++++搜索匹配",fundlist.get(i).getFund_symbol());
                        if(symbol.equals(fundlist.get(i).getFund_symbol())){

                            resfund_list.add(fundlist.get(i));
                            flag = true;
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                            break;
                        }else {
                            i++;
                        }

                    }
                    if(flag == false){
                        System.out.println("wrong!");
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    }


                }
                catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
