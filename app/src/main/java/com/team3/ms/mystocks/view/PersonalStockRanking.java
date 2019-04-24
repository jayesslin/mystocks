package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IncomeLossObject;
import com.team3.ms.mystocks.entity.IncomeandLoss;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.rankadapter;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PersonalStockRanking extends AppCompatActivity {
    private ListView list_stockview;
    private Context mContext;
    private List<stocklist> stock_list=new ArrayList<>();
    private rankadapter mAdapter=null;
    private Handler handler;
//    private TextView total_IandL,daily_iandL;
    private TextView Sell;
    private List<IncomeLossObject> inandloss =new ArrayList<>();
    private List<stocklist> inlo_list=new ArrayList<>();
    private List<stocklist> sort_list=new ArrayList<>();
    //private List<IncomeLossObject> inandloss1 =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stock_ranking);
        list_stockview=(ListView)findViewById(R.id.stockslist);
        Sell = (TextView) findViewById(R.id.daily_iandL);
        ImageView backButton = (ImageView) findViewById(R.id.backH);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),homePage.class);
                startActivity(s1);
            }
        });
        ImageView AllstockButton = (ImageView) findViewById(R.id.stock_w);
        AllstockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(s1);
            }
        });



        //定义两个视图，两个视图都加载同一个布局文件list_view.ml
//        View view3 = getLayoutInflater().inflate(R.layout.activity_daily_income_page_listview,null);

        //——————————————————————————————————重点理解——————————————————————————————————
        // 原来findviewById是View这个类中的方法，默认调用时其实应该是：this.findviewById();
        //由于listview标签的声明并不在当前的viewPager所在的xml布局中，所以直接通过findviewById方法是不能得到该listview的实例的。所以我们要用view1.findViewById（）方法找到listview
//        final ListView listView1 = (ListView) view3.findViewById(R.id.listview1);
        //final ListView listView2 = (ListView) view2.findViewById(R.id.listview);
        //———————————————————————————————————重点理解——————————————————————————————————

        final ListView listView = (ListView) findViewById(R.id.listview1);
        get();

        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    IncomeandLoss il = new IncomeandLoss();
                    double[] record = new double[5];
                    double[] rank = new double[5];
                    double BABA = Double.parseDouble(stock_list.get(0).getLastestpri());
                    double BABA_open = Double.parseDouble(stock_list.get(0).getOpenpri());
                    BABA*=100;
                    BABA_open*=100;
                    BigDecimal temp_a   =   new   BigDecimal(BABA-BABA_open);
                    double   profit   =   temp_a.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    record[0] = profit;
                    rank[0] = profit;
                    if(!inlo_list.contains(stock_list.get(0))){
                        inlo_list.add(stock_list.get(0));
                    }
                    double PEP = Double.parseDouble(stock_list.get(1).getLastestpri());
                    double PEP_open = Double.parseDouble(stock_list.get(1).getOpenpri());
                    PEP*=200;
                    PEP_open*=200;
                    BigDecimal temp_b   =   new   BigDecimal(PEP-PEP_open);
                    double   profit1   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    record[1] = profit1;
                    rank[1] = profit1;
                    if(!inlo_list.contains(stock_list.get(1))){
                        inlo_list.add(stock_list.get(1));
                    }
                    double AAPL = Double.parseDouble(stock_list.get(2).getLastestpri());
                    double AAPL_open = Double.parseDouble(stock_list.get(2).getOpenpri());
                    AAPL*=50;
                    AAPL_open*=50;
                    BigDecimal temp_c   =   new   BigDecimal(AAPL-AAPL_open);
                    double   profit2   =   temp_c.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    record[2] = profit2;
                    rank[2] = profit2;
                    if(!inlo_list.contains(stock_list.get(2))){
                        inlo_list.add(stock_list.get(2));
                    }
                    double AMZN = Double.parseDouble(stock_list.get(3).getLastestpri());
                    double AMZN_open = Double.parseDouble(stock_list.get(3).getOpenpri());
                    AMZN*=30;
                    AMZN_open*=30;
                    BigDecimal temp_d   =   new   BigDecimal(AMZN-AMZN_open);
                    double   profit3   =   temp_d.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    record[3] = profit3;
                    rank[3] = profit3;
                    if(!inlo_list.contains(stock_list.get(3))){
                        inlo_list.add(stock_list.get(3));
                    }
                    double FB = Double.parseDouble(stock_list.get(4).getLastestpri());
                    double FB_open = Double.parseDouble(stock_list.get(4).getOpenpri());
                    FB*=20;
                    FB_open*=20;
                    BigDecimal temp_e   =   new   BigDecimal(FB-FB_open);
                    double   profit4   =   temp_e.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    record[4] = profit4;
                    rank[4] = profit4;
                    if(!inlo_list.contains(stock_list.get(4))){
                        inlo_list.add(stock_list.get(4));
                    }
                    Arrays.sort(rank);
                    for(int i=4;i>=0;i--){
                        double tmp=rank[i];
                        int ind=0;
                        while(ind<5&&record[ind]!=tmp) ind++;
                        sort_list.add(inlo_list.get(ind));
                    }
//                    Collections.sort(inlo_list, new Comparator<stocklist>() {
//                        @Override
//                        public int compare(stocklist o1, stocklist o2) {
//                            int num1=0,num2=0;
//                            if(o1.getGid().equals("BABA"))
//                                num1=100;
//                            else if(o1.getGid().equals("PEP"))
//                                num1=200;
//                            else if(o1.getGid().equals("AAPL"))
//                                num1=50;
//                            else if(o1.getGid().equals("AMZN"))
//                                num1=30;
//                            else if(o1.getGid().equals("FB"))
//                                num1=20;
//                            if(o2.getGid().equals("BABA"))
//                                num2=100;
//                            else if(o1.getGid().equals("PEP"))
//                                num2=200;
//                            else if(o1.getGid().equals("AAPL"))
//                                num2=50;
//                            else if(o1.getGid().equals("AMZN"))
//                                num2=30;
//                            else if(o1.getGid().equals("FB"))
//                                num2=20;
//                            double val1 = num1*(Double.parseDouble(o1.getLastestpri())-Double.parseDouble(o1.getOpenpri()));
//                            double val2 = num2*(Double.parseDouble(o2.getLastestpri())-Double.parseDouble(o2.getOpenpri()));
////                            Log.v("2222222222222++++++++++", val1+"  "+val2);
//                            if(val1<val2) return 1;
//                            else if(val1>val2) return -1;
//                            else return 0;
//                        }
//                    });
//                    for(int nnn=0;nnn<inlo_list.size();nnn++){
//                        Double d = Double.parseDouble(inlo_list.get(nnn).getLastestpri())-Double.parseDouble(inlo_list.get(nnn).getOpenpri());
//                        Log.v("11111111+++++++++++", inlo_list.get(nnn).getGid()+"   "+d+"   ");
//                    }
                    mAdapter=new rankadapter(sort_list, PersonalStockRanking.this,inandloss);
                    listView.setAdapter( mAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            final AlertDialog.Builder normalDialog =
                                    new AlertDialog.Builder(PersonalStockRanking.this);
                            normalDialog.setIcon(R.drawable.ic_launcher_background);
                            normalDialog.setTitle("Confirm Sell?");
                            final stocklist s = sort_list.get(position);
                            normalDialog.setPositiveButton("YES",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            for(int si=0;si<sort_list.size();si++){
                                                if(sort_list.get(si).getGid().equals(s.getGid())){
                                                    sort_list.remove(si);
                                                    break;
                                                }
                                            }
                                            mAdapter=new rankadapter(sort_list, PersonalStockRanking.this,inandloss);
                                            listView.setAdapter( mAdapter);
                                        }
                                    });
                            normalDialog.setNegativeButton("NO",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent startIntent = new Intent(getApplicationContext(),PersonalStockRanking.class);
                                            startActivity(startIntent);
                                        }
                                    });
                            // 显示
                            normalDialog.show();

                        }
                    });


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
