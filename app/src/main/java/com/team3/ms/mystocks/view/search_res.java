package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class search_res extends AppCompatActivity {
    private ListView searchList;
    private Button goButton;
    private Button backButton;
    private EditText searchText;
    private stockAdapter mAdapter=null;
    private Context resContext;
    private Handler handler;
    private List<stocklist> stock_list=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_res);
        String symbol=getIntent().getStringExtra("symbol");
        get_list(symbol);
        searchText = (EditText)findViewById(R.id.symbolt);
        goButton = (Button)findViewById(R.id.got);
        backButton = (Button)findViewById(R.id.back);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symbol = searchText.getText().toString();
                get_list(symbol);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),homePage.class);
                startActivity(s1);
            }
        });
        resContext = search_res.this;
        searchList = (ListView)findViewById(R.id.res_list);
        handler =new Handler(){
            public void handleMessage(Message msg){
                System.out.println(msg.what);
                if(msg.what == 1){
                    /**/

                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/

                    mAdapter=new stockAdapter(stock_list,resContext);
                    searchList.setAdapter(mAdapter);
                    searchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            stocklist a=stock_list.get(position);
                            Intent intent=new Intent(search_res.this, stock_detail.class);
                            System.out.println(a.getname());
                            intent.putExtra("gid",a.getname());
                            startActivity(intent);

                        }
                    });

                }else{
                    System.out.println("No");
                    Toast.makeText(search_res.this,"The stock you are looking for does not exist!!!",(int)2000).show();
                }
            }

        };
    }

    private void get_list(final String search_text){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();

                    String result=sp.getRequest5(1);
                    System.out.println("my11" + search_text);

                    JSONObject object1=new JSONObject(result);
                    JSONObject object2=object1.getJSONObject("result");
                    JSONArray array1=object2.getJSONArray("data");
                    int i = 0;
                    boolean flag = false;
                    while(flag == false && i < 20){
                        System.out.print(i);

                        JSONObject a=array1.getJSONObject(i);
                        String gid =a.getString("symbol");
                        System.out.println("my" + gid);
                        if(search_text.equals(gid)){
                            String name=a.getString("cname");

                            String openpri =a.getString("open");
                            String lastestpri =a.getString("price");
                            String uppic =a.getString("high");
                            String limit =a.getString("chg");
                            stocklist stock=new stocklist(gid,openpri,lastestpri,limit);
                            stock_list.add(stock);
                            System.out.println(stock);
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


                catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }



}
