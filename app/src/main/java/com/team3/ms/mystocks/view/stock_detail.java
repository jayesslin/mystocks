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
import android.widget.ImageView;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.entity.stockdetail;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.GetImageByUrl;
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
    private ImageView search_bt;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockdetail);
        ImageView backButton = (ImageView)findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s2 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(s2);
            }
        });
        gid=getIntent().getStringExtra("gid");
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

        handler =new Handler(){
            public void handleMessage(Message msg){
                if(msg.what == 1){

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
                    //Log.i("bb",stock_detaillist.get(0).getname());


                    txt_EPS.setText("CompanyName————"+stock_detaillist.get(0).getCompanyName());
                    txt_symbol1.setText("Symbol————"+stock_detaillist.get(0).getgid());
                    txt_laststpri.setText("Lastprice————"+stock_detaillist.get(0).getlastestpri());
                    txt_maxpri.setText("Max price————"+stock_detaillist.get(0).getHigh());
                    txt_minpri.setText("Min price————"+stock_detaillist.get(0).getLow());
                    if(stock_detaillist.get(0).getColor().equals("red")){
                        txt_limit.setTextColor(android.graphics.Color.RED);
                        txt_divident.setTextColor(android.graphics.Color.RED);
                    }else{
                        txt_limit.setTextColor(Color.GREEN);
                        txt_divident.setTextColor(Color.GREEN);
                    }
                    txt_limit.setText("change————"+stock_detaillist.get(0).getChangePercent());
                    txt_traAmount.setText("Open————"+stock_detaillist.get(0).getOpen());

                    txt_divident.setText("Upper price————"+stock_detaillist.get(0).getUppic());
                    txt_afterpic.setText("Week52High————"+stock_detaillist.get(0).getWeek52High());
                    txt_afterlimit.setText("Week52low————"+stock_detaillist.get(0).getWeek52low());
                    txt_ustime.setText("US date————"+stock_detaillist.get(0).getustime());
                    /*ImageView imgview=(ImageView)findViewById(R.id.stockdetailimg);
                    GetImageByUrl im= new GetImageByUrl();
                    im.setImage(imgview,stock_detaillist.get(0).getimg());*/



                }

            }

        };










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
                        String result=sp.getStocklist();

                        JSONArray  array1=new JSONArray(result);
                        JSONObject a = array1.getJSONObject(0);
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
                        stockdetail stock = new stockdetail(companyName,gid,openpri,lastestpri,high,low,uppic,changePercent,week52High,week52Low,ustime);



                     /*   String result1=sp.getRequest(gid);

                        JSONObject object1=new JSONObject(result1);
                        JSONArray array11=object1.getJSONArray("result");
                        JSONObject object2=array11.getJSONObject(0);

                        JSONObject object4=object2.getJSONObject("gopicture");
                        String img=object4.getString("minurl");
                        stock.setImg(img);*/
                        stock_detaillist.add(stock);



                    /*String result=sp.getRequest(gid);

                    JSONObject object1=new JSONObject(result);
                    JSONArray array1=object1.getJSONArray("result");
                    JSONObject object2=array1.getJSONObject(0);


                    JSONObject object3=object2.getJSONObject("data");
                    JSONObject object4=object2.getJSONObject("gopicture");
                    String img=object4.getString("minurl");



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
                            //Log.i("a",img);

                            stockdetail stock = new stockdetail(name,gid,lastestpri,maxpri,minpri,limit,traAmount,EPS,uppic,afterpic,afterlimt,ustime,img);

                    stock_detaillist.add(stock);
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
        }).start();


    }

}
