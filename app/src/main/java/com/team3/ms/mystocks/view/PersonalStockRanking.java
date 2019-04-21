package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IncomeLossObject;
import com.team3.ms.mystocks.entity.IncomeandLoss;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stock_inlo_adapter;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
    private Stock_inlo_adapter mAdapter=null;
    private Stock_inlo_adapter mAdapter1=null;
    private Handler handler;
    private TextView total_IandL,daily_iandL;
    private List<IncomeLossObject> inandloss =new ArrayList<>();
    private List<stocklist> inlo_list=new ArrayList<>();
    //private List<IncomeLossObject> inandloss1 =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_stock_ranking);
        list_stockview=(ListView)findViewById(R.id.stockslist);
        total_IandL = (TextView) findViewById(R.id.total_IandL);
        daily_iandL = (TextView) findViewById(R.id.daily_iandL);
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
        get();
       /* mytab = (TabLayout) findViewById(R.id.mytab);

        mytab.addTab(mytab.newTab().setText("Income").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("Loss").setIcon(R.mipmap.ic_launcher));
*/
        total_IandL.setText("99999");
        daily_iandL.setText("-203");

        //找到ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //定义一个视图集合（用来装左右滑动的页面视图）
        final List<View> viewList = new ArrayList<View>();

        //定义两个视图，两个视图都加载同一个布局文件list_view.ml
        View view1 = getLayoutInflater().inflate(R.layout.activity_daily_income_page_listview,null);
        //View view2 = getLayoutInflater().inflate(R.layout.activity_daily_income_page_listview,null);

        //将两个视图添加到视图集合viewList中
        viewList.add(view1);
        //viewList.add(view2);

        //为ViewPager设置适配器
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                //这个方法是返回总共有几个滑动的页面（）
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                //该方法判断是否由该对象生成界面。
                return view==object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //这个方法返回一个对象，该对象表明PagerAapter选择哪个对象放在当前的ViewPager中。这里我们返回当前的页面
                viewPager.addView(viewList.get(position));
                return viewList.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //这个方法从viewPager中移动当前的view。（划过的时候）
                viewPager.removeView(viewList.get(position));
            }

        });



//        TabLayout tabLayout= (TabLayout) findViewById(R.id.id_tl);
//        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(0).setText("Income");
//        tabLayout.getTabAt(1).setText("Loss");
        //——————————————————————————————————重点理解——————————————————————————————————
        // 原来findviewById是View这个类中的方法，默认调用时其实应该是：this.findviewById();
        //由于listview标签的声明并不在当前的viewPager所在的xml布局中，所以直接通过findviewById方法是不能得到该listview的实例的。所以我们要用view1.findViewById（）方法找到listview
        final ListView listView1 = (ListView) view1.findViewById(R.id.listview);
        //final ListView listView2 = (ListView) view2.findViewById(R.id.listview);
        //———————————————————————————————————重点理解——————————————————————————————————

        //这里我们传入数据
        String[] data = {"1","2","3","4","5","6","7","8","9","10","11","12","13"};

        //android.R.layout.simple_list_item_1是android自带的一个布局，只有一个textview
        //list_stockview=(ListView)findViewById(listView);
        //mAdapter=new stockAdapter(stock_list,dailyIncAndLos.this);
        //list_stockview.setAdapter(mAdapter);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);

        //为ListView设置适配器
       /* listView1.setAdapter(adapter);
        listView2.setAdapter(adapter);*/
        /*listView1.setAdapter( mAdapter);
        listView2.setAdapter( mAdapter);*/

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
                    double[] record = new double[5];
                    double BABA = Double.parseDouble(stock_list.get(0).getLastestpri());
                    double BABA_open = Double.parseDouble(stock_list.get(0).getOpenpri());
                    BABA*=100;
                    BABA_open*=100;
                    record[0] = BABA-BABA_open;
                    if(!inlo_list.contains(stock_list.get(0))){
                        inlo_list.add(stock_list.get(0));
                    }
                    double PEP = Double.parseDouble(stock_list.get(1).getLastestpri());
                    double PEP_open = Double.parseDouble(stock_list.get(1).getOpenpri());
                    PEP*=200;
                    PEP_open*=200;
                    record[1] = PEP-PEP_open;
                    if(!inlo_list.contains(stock_list.get(1))){
                        inlo_list.add(stock_list.get(1));
                    }
                    double AAPL = Double.parseDouble(stock_list.get(2).getLastestpri());
                    double AAPL_open = Double.parseDouble(stock_list.get(2).getOpenpri());
                    AAPL*=50;
                    AAPL_open*=50;
                    record[2] = AAPL-AAPL_open;
                    if(!inlo_list.contains(stock_list.get(2))){
                        inlo_list.add(stock_list.get(2));
                    }
                    double AMZN = Double.parseDouble(stock_list.get(3).getLastestpri());
                    double AMZN_open = Double.parseDouble(stock_list.get(3).getOpenpri());
                    AMZN*=30;
                    AMZN_open*=30;
                    record[3] = AMZN-AMZN_open;
                    if(!inlo_list.contains(stock_list.get(3))){
                        inlo_list.add(stock_list.get(3));
                    }
                    double FB = Double.parseDouble(stock_list.get(4).getLastestpri());
                    double FB_open = Double.parseDouble(stock_list.get(4).getOpenpri());
                    FB*=20;
                    FB_open*=20;
                    record[4] = FB-FB_open;
                    if(!inlo_list.contains(stock_list.get(4))){
                        inlo_list.add(stock_list.get(4));
                    }
                    total = total_origin-(BABA+PEP+AAPL+AMZN+FB);
                    double daily = (BABA_open+PEP_open+AAPL_open+AMZN_open+FB_open)-(BABA+PEP+AAPL+AMZN+FB);
                    Log.i("++++++++","total大小是" + total);
                    if(total>0){
                        total_IandL.setTextColor(android.graphics.Color.GREEN);
                    }else{
                        total_IandL.setTextColor(Color.RED);
                    }

                    BigDecimal a   =   new   BigDecimal(total);
                    double   f2   =   a.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    String res = f2+"$";
                    total_IandL.setText(res);
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
                    List<stocklist> income_list=new ArrayList<>();
                    List<stocklist> loss_list=new ArrayList<>();


                    for(int j =0 ; j <5;j++){
                        Log.i("++++++++ss++",""+stock_list.size());
                        if(Double.parseDouble(stock_list.get(j).getLastestpri())>Double.parseDouble(stock_list.get(j).getOpenpri())){
                            income_list.add(stock_list.get(j));
                            IncomeLossObject object = new IncomeLossObject();
                            object.setDate(il.getStock_date()[j]);
                            object.setShares(il.getStock_shares()[j]);
//                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
//                            Double temp_profit = record[j];
//                            BigDecimal temp_a   =   new   BigDecimal(temp_profit);
//                            double   profit   =   temp_a.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
                            BigDecimal temp_a   =   new   BigDecimal(temp_profit);
                            double   profit   =   temp_a.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                            object.setProfit(profit+"");
                            inandloss.add(object);
                            //profit1 += Double.parseDouble(temp_profit);
                            profit1 += profit;
                        }
                        else{
                            loss_list.add(stock_list.get(j));
                            IncomeLossObject a1 = new IncomeLossObject();
                            a1.setDate(il.getStock_date()[j]);
                            a1.setShares(il.getStock_shares()[j]);
                            //String temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit())+"";
//                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
//                            Double temp_profit = record[j];
////                            rank1[j] = 0.0 - temp_profit;
//                            BigDecimal temp_b   =   new   BigDecimal(temp_profit);
//                            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                            Double temp_profit = (Integer.parseInt(il.getStock_shares()[j])) *  Double.parseDouble(stock_list.get(j).getLimit());
                            BigDecimal temp_b   =   new   BigDecimal(temp_profit);
                            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                            a1.setProfit(profit+"");
                            inandloss.add(a1);
                            // profit2 += Double.parseDouble(temp_profit);
                            profit2 += profit;
                        }

                    }
                    double daily_profit = profit1+ profit2;
                    String s = daily_profit+"$";
                    daily_iandL.setText(s);
                    if(daily_profit>0){
                        daily_iandL.setTextColor(android.graphics.Color.GREEN);
                    }else{
                        daily_iandL.setTextColor(Color.RED);
                    }
//                    double[] rank=new double[5];
//                    for(int r=0;r<5;r++){
//                        rank[r] = record[r];
//                    }
//                    Arrays.sort(rank);
//                    for(int rr=4;rr>=0;rr--){
//                        for(int ind=0;ind<5;ind++){
//                            if(record[ind]==rank[rr]){
//                                Log.v("cccccccccccccccc", record[ind]+"");
//                                inlo_list.add(stock_list.get(ind));
//                                break;
//                            }
//                        }
//                    }
                    Log.v("beforeeeeeeeeeeeeeeeeeeee:", inlo_list.size()+"  "+stock_list.size());
//                    Collections.sort(inlo_list, new Comparator<stocklist>() {
//                        @Override
//                        public int compare(stocklist o1, stocklist o2) {
//
//                            double val1 = Double.parseDouble(o1.getLastestpri())-Double.parseDouble(o1.getOpenpri());
//                            double val2 = Double.parseDouble(o2.getLastestpri())-Double.parseDouble(o2.getOpenpri());
//                            if(val1>val2) return 1;
//                            return -1;
//                        }
//                    });
                    Collections.sort(inlo_list, new Comparator<stocklist>() {
                        @Override
                        public int compare(stocklist o1, stocklist o2) {

                            double val1 = Double.parseDouble(o1.getLastestpri())-Double.parseDouble(o1.getOpenpri());
                            double val2 = Double.parseDouble(o2.getLastestpri())-Double.parseDouble(o2.getOpenpri());
                            if(val1<val2) return 1;
                            return -1;
                        }
                    });
                    mAdapter=new Stock_inlo_adapter(inlo_list, PersonalStockRanking.this,inandloss);
//                    mAdapter1=new Stock_inlo_adapter(loss_list, PersonalStockRanking.this,inandloss);
                    listView1.setAdapter( mAdapter);
//                    Collections.sort(income_list, new Comparator<stocklist>() {
//                        @Override
//                        public int compare(stocklist o1, stocklist o2) {
//
//                            double val1 = Double.parseDouble(o1.getLastestpri())-Double.parseDouble(o1.getOpenpri());
//                            double val2 = Double.parseDouble(o2.getLastestpri())-Double.parseDouble(o2.getOpenpri());
//                            if(val1>val2) return 1;
//                            return -1;
//                        }
//                    });
//                    for(int si=0;si<income_list.size();si++){
//                        inlo_list.add(income_list.get(si));
//                    }


//                    listView1.setAdapter( mAdapter1);
//                    Log.v("lengthhhhhhhhhhhhhhhhhhhhh:", inlo_list.size()+"  "+stock_list.size());
//                    mAdapter=new Stock_inlo_adapter(inlo_list, PersonalStockRanking.this, inandloss);
//                    listView1.setAdapter( mAdapter);

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
