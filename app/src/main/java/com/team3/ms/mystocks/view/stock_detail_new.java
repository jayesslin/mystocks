package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.controller.collectcontroller;
import com.team3.ms.mystocks.entity.stockdetail;
import com.team3.ms.mystocks.tools.GetImageByUrl;
import com.team3.ms.mystocks.tools.Stocks_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class stock_detail_new extends AppCompatActivity {
    private String gid;
    private List<stockdetail> stock_detaillist=new ArrayList<>();
    private Handler handler;
    private ImageView search_bt;
    private TextView company,latestprice,change_per,primaryExchange,symbol_c,open_c,close_c,week52high_c, week52low_c,avl_volume_c,volume_c,pe_rotio_c,marketcap_c;
    private stockdetail stock;
    private dbmanage dbMgr;
    private TextView collectV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockdetail_new);
        dbMgr = new dbmanage(stock_detail_new.this, "MyStocks.db", null, 1);

        ImageView backButton = (ImageView)findViewById(R.id.backButton);
        Typeface collectfont = Typeface.createFromAsset(getAssets(), "iconfont/collect.ttf");
        collectV = (TextView)findViewById(R.id.collect);
        collectV.setTypeface(collectfont);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s2 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(s2);
            }
        });
        gid=getIntent().getStringExtra("gid");
        if(dbMgr.vertify_symbol(gid) != null){
            collectV.setTextColor(0xffffff00);
        }
        //收藏
        collectV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("collect_symbol:",gid);
                collectcontroller cc = new collectcontroller();
                boolean res = cc.collect_stock(dbMgr, gid);
                if(res == true){
                    collectV.setTextColor(0xffffff00);
                    Toast.makeText(stock_detail_new.this,"The stock is collected into my stock successfully!",(int)2000).show();
                }else{
                    collectV.setTextColor(0xff000000);
                    Toast.makeText(stock_detail_new.this,"The stock is removed from my stock!",(int)2000).show();
                }
            }
        });
//        Log.i("***********","gid: "+gid);
        //搜索跳转
        search_bt = (ImageView)findViewById(R.id.search_bt);
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss11 = new Intent(getApplicationContext(),Inquery.class);
                startActivity(sss11);
            }
        });
        getgid(gid);
        //getImage(gid);
        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){
                    company=(TextView) findViewById(R.id.Company);
                    latestprice=(TextView) findViewById(R.id.LatestPrice);
                    change_per=(TextView) findViewById(R.id.ChangeandPercent);
                    primaryExchange   =(TextView) findViewById(R.id.Exchange);
                    company.setText(stock_detaillist.get(0).getCompanyName());
                    latestprice.setText(stock_detaillist.get(0).getlastestpri());
                    change_per.setText(stock_detaillist.get(0).getUppic()+"("+Double.parseDouble(stock_detaillist.get(0).getChangePercent())+"%)");
                    primaryExchange.setText(stock_detaillist.get(0).getPrimaryExchange());
                    if(stock_detaillist.get(0).getColor().equals("red")){
                        change_per.setTextColor(android.graphics.Color.RED);
                    }else{
                        change_per.setTextColor(Color.GREEN);
                    }

                    symbol_c=(TextView) findViewById(R.id.symbol_c);
                    open_c=(TextView) findViewById(R.id.open_c);
                    close_c=(TextView) findViewById(R.id.close_c);
                    week52high_c=(TextView) findViewById(R.id.week52high_c);
                    week52low_c=(TextView) findViewById(R.id.week52low_c);
                    avl_volume_c=(TextView) findViewById(R.id.avgVolume_c);
                    volume_c=(TextView) findViewById(R.id.Volume_c);
                    pe_rotio_c=(TextView) findViewById(R.id.peRatio_c);
                    marketcap_c=(TextView) findViewById(R.id.MarketCap_c);

                    symbol_c.setText(stock_detaillist.get(0).getgid());
                    open_c.setText(stock_detaillist.get(0).getOpen());
                    close_c.setText(stock_detaillist.get(0).getClose());
                    week52high_c.setText(stock_detaillist.get(0).getWeek52High());
                    week52low_c.setText(stock_detaillist.get(0).getWeek52low());
                    avl_volume_c.setText(stock_detaillist.get(0).getAvl_volume());
                    volume_c.setText(stock_detaillist.get(0).getLatestVolume());
                    pe_rotio_c.setText(stock_detaillist.get(0).getPe_ratio());
                    marketcap_c.setText(stock_detaillist.get(0).getMarketCap());

                    //Log.i("*************mg:   ",stock_detaillist.get(0).getimg());
                    ImageView imgview=(ImageView)findViewById(R.id.stockdetailimg);
                    GetImageByUrl im= new GetImageByUrl();
                    im.setImage(imgview,stock_detaillist.get(0).getimg());
                }

            }

        };
    }
    private void getImage(final String gid) {
        new Thread(new Runnable() {
            public void run () {
            try {

                Stocks_provider sp = new Stocks_provider();
                String result1 = sp.getRequest(gid);
                JSONObject object1 = new JSONObject(result1);
                JSONArray array11 = object1.getJSONArray("result");
                JSONObject object2 = array11.getJSONObject(0);
                JSONObject object4 = object2.getJSONObject("gopicture");
                String img = object4.getString("minurl");
                Log.i("***********",img);
                stock.setImg(img);
            }
        catch(JSONException e){
                e.printStackTrace();
            } catch(Exception e){
                e.printStackTrace();
            }
        }
    }).start();
    }
    private void getgid(final String gid){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{ Stocks_provider sp=new Stocks_provider();
                    String  url_head="https://cloud.iexapis.com/beta/stock/";
                    String url_nil ="/quote?displayPercent=true&token=pk_d58ac872888a44d0915cce73e9849e32";
                    String quote=gid;
                    String url_request=url_head+quote+url_nil;
                    String result=sp.getaStocklist(url_request);
                    JSONObject  a =new JSONObject (result);
                    String companyName = a.getString("companyName");
                    String gid = a.getString("symbol");
                    String openpri = a.getString("open");
                    String lastestpri = a.getString("latestPrice");
                    String high = a.getString("high");
                    String low = a.getString("low");
                    String uppic = a.getString("change");
                    String changePercent = a.getString("changePercent");
                    String week52High = a.getString("week52High");
                    String week52Low = a.getString("week52Low");
                    String ustime=a.getString("latestTime");
                    String pe_ratio=a.getString("peRatio");
                    String marketCap=a.getString("marketCap");
                    String avl_volume =a.getString("avgTotalVolume");
                    String latestVolume=a.getString("latestVolume");
                    String close = a.getString("close");
                    stock = new stockdetail(companyName,gid,openpri,lastestpri,high,low,uppic,changePercent,week52High,week52Low,ustime);
                    //这个上市地点改为最后日期
                    String primaryExchange = a.getString("latestTime");
                    stock.setPrimaryExchange(primaryExchange);
                    stock.setPe_ratio(pe_ratio);
                    stock.setMarketCap(marketCap);
                    stock.setAvl_volume(avl_volume);
                    stock.setLatestVolume(latestVolume);
                    stock.setClose(close);
                    //设置图片
                    String result1 = sp.getRequest(gid);
                    JSONObject object1 = new JSONObject(result1);
                    JSONArray array11 = object1.getJSONArray("result");
                    JSONObject object2 = array11.getJSONObject(0);
                    JSONObject object4 = object2.getJSONObject("gopicture");
                    String img = object4.getString("minurl");
                    Log.i("***********",img);
                    stock.setImg(img);
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
