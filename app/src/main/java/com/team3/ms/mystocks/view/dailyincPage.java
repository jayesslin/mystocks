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
import com.team3.ms.mystocks.entity.IncomeandLoss;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.MyPagerAdapter;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.stockAdapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import static com.team3.ms.mystocks.R.id.listView;
import static com.team3.ms.mystocks.R.id.stockslist;

public class dailyincPage extends AppCompatActivity {
    private ListView list_stockview;
    private Context mContext;
    private List<stocklist> stock_list=new ArrayList<>();
    private stockAdapter mAdapter=null;
    private stockAdapter mAdapter1=null;
    private Handler handler;
    private TextView total_IandL,daily_iandL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_test);
        list_stockview=(ListView)findViewById(R.id.stockslist);
        total_IandL = (TextView) findViewById(R.id.total_IandL);
        daily_iandL = (TextView) findViewById(R.id.daily_iandL);
        get();
       /* mytab = (TabLayout) findViewById(R.id.mytab);

        mytab.addTab(mytab.newTab().setText("Income").setIcon(R.mipmap.ic_launcher));
        mytab.addTab(mytab.newTab().setText("Loss").setIcon(R.mipmap.ic_launcher));
*/
        total_IandL.setText("+99999");
        daily_iandL.setText("-203");

        //找到ViewPager
        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //定义一个视图集合（用来装左右滑动的页面视图）
        final List<View> viewList = new ArrayList<View>();

        //定义两个视图，两个视图都加载同一个布局文件list_view.ml
        View view1 = getLayoutInflater().inflate(R.layout.activity_daily_income_page_listview,null);
        View view2 = getLayoutInflater().inflate(R.layout.activity_daily_income_page_listview,null);

        //将两个视图添加到视图集合viewList中
        viewList.add(view1);
        viewList.add(view2);

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



        /*List<View> views=new ArrayList<>();
        TextView textView=new TextView(this);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setText("Income");
        textView.setGravity(Gravity.CENTER);
        views.add(textView);
        TextView textView1=new TextView(this);
        LinearLayout.LayoutParams layoutParams1=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView1.setLayoutParams(layoutParams1);
        textView1.setText("Loss");
        textView1.setGravity(Gravity.CENTER);
        views.add(textView1);
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(views);
        viewPager.setAdapter(myPagerAdapter);*/
        TabLayout tabLayout= (TabLayout) findViewById(R.id.id_tl);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText("Income");
        tabLayout.getTabAt(1).setText("Loss");
        //——————————————————————————————————重点理解——————————————————————————————————
        // 原来findviewById是View这个类中的方法，默认调用时其实应该是：this.findviewById();
        //由于listview标签的声明并不在当前的viewPager所在的xml布局中，所以直接通过findviewById方法是不能得到该listview的实例的。所以我们要用view1.findViewById（）方法找到listview
        final ListView listView1 = (ListView) view1.findViewById(R.id.listview);
        final ListView listView2 = (ListView) view2.findViewById(R.id.listview);
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
                    double BABA = Double.parseDouble(stock_list.get(0).getLastestpri());
                    double BABA_open = Double.parseDouble(stock_list.get(0).getOpenpri());
                    BABA*=100;
                    BABA_open*=100;
                    double PEP = Double.parseDouble(stock_list.get(1).getLastestpri());
                    double PEP_open = Double.parseDouble(stock_list.get(1).getOpenpri());
                    PEP*=200;
                    PEP_open*=200;
                    double AAPL = Double.parseDouble(stock_list.get(2).getLastestpri());
                    double AAPL_open = Double.parseDouble(stock_list.get(2).getOpenpri());
                    AAPL*=50;
                    AAPL_open*=50;
                    double AMZN = Double.parseDouble(stock_list.get(3).getLastestpri());
                    double AMZN_open = Double.parseDouble(stock_list.get(3).getOpenpri());
                    AMZN*=30;
                    AMZN_open*=30;
                    double FB = Double.parseDouble(stock_list.get(4).getLastestpri());
                    double FB_open = Double.parseDouble(stock_list.get(4).getOpenpri());
                    FB*=20;
                    FB_open*=20;
                    total = total_origin-(BABA+PEP+AAPL+AMZN+FB);
                    double daily = (BABA_open+PEP_open+AAPL_open+AMZN_open+FB_open)-(BABA+PEP+AAPL+AMZN+FB);
                    Log.i("++++++++","total大小是" + total);
                    if(total>0){
                        total_IandL.setTextColor(android.graphics.Color.GREEN);
                    }else{
                        total_IandL.setTextColor(Color.RED);
                    }
                    if(daily>0){
                        daily_iandL.setTextColor(android.graphics.Color.GREEN);
                    }else{
                        daily_iandL.setTextColor(Color.RED);
                    }

                    BigDecimal b   =   new   BigDecimal(daily);
                    double   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal a   =   new   BigDecimal(total);
                    double   f2   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
                    String res_daily=f1+"$";
                    String res = f2+"$";
                    total_IandL.setText(res);
                    daily_iandL.setText(res_daily);
                    /**/
                    /*for (int i = 0 ; i < stock_list.size() ; i++)
                        Log.d("value is" , stock_list.get(i).toString());*/
                    /* mAdapter=new stockAdapter(stock_list,mContext);*/
                    List<stocklist> income_list=new ArrayList<>();
                    List<stocklist> loss_list=new ArrayList<>();
                    double[] buy_price = il.getStock_price();
                    for(int j =0 ; j <5;j++){
                        if(Integer.parseInt(stock_list.get(j).getLastestpri())>buy_price[j]){
                            income_list.add(stock_list.get(j));
                        }
                        else{
                            loss_list.add(stock_list.get(j));
                        }
                    }
                    mAdapter=new stockAdapter(income_list,dailyincPage.this);
                    mAdapter1=new stockAdapter(loss_list,dailyincPage.this);
                    listView1.setAdapter( mAdapter);
                    listView2.setAdapter( mAdapter1);
                    /*list_stockview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            stocklist a=stock_list.get(position);
                            Intent intent=new Intent(dailyIncAndLos.this, stock_detail_new.class);
                            intent.putExtra("gid",a.getGid());
                            startActivity(intent);
                        }
                    }*/

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