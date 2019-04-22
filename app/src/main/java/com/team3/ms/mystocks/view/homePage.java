package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team3.ms.mystocks.Adapter.IconAdapter;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IconBean;
import com.team3.ms.mystocks.entity.news;
import com.team3.ms.mystocks.tools.News_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class homePage extends AppCompatActivity {
    private List<IconBean> mIconBeenList = new ArrayList<>();
    private ListView lv;
    private TextView main_home,Stock;
    private ImageView search_bt,imageView8,imageView10;
    private ArrayList<String> news_webview;
    private NavigationView navi_v;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // 定义 ListView组件
        lv=(ListView)findViewById(R.id.listView);
        Intent s1 =getIntent();
        final String userName = s1.getStringExtra("id");//获得用户名
        //initData();
        //lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));
        new GetNewsData().execute();
        //lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));

        //home logo 跳转
        imageView8 = (ImageView)findViewById(R.id.homeimg);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),homePage.class);

                ss11.putExtra("id",userName);
                startActivity(ss11);
                finish();
            }
        });
        main_home =(TextView)findViewById(R.id.main_home1);
        main_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),homePage.class);

                ss11.putExtra("id",userName);
                startActivity(ss11);
                finish();
            }
        });
        //stock logo 跳转
        imageView10 = (ImageView)findViewById(R.id.stockicon);
        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),Allstocks.class);

                ss11.putExtra("id",userName);
                startActivity(ss11);
                finish();
            }
        });
        Stock = (TextView)findViewById(R.id.Stock1);
        Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),Allstocks.class);

                ss11.putExtra("id",userName);
                startActivity(ss11);
                finish();
            }
        });

        search_bt = (ImageView)findViewById(R.id.search_bt);
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss11 = new Intent(getApplicationContext(),Inquery.class);
                startActivity(sss11);

            }
        });
        //Navigation bar
        navi_v = findViewById(R.id.nav_view);
        navi_v.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                int id = menuItem.getItemId();
                if(id == R.id.item1)
                {
                    Intent intent = new Intent();
                    intent.setClass(homePage.this,mystock.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.item2)
                {
                    Intent intent = new Intent();
                    intent.setClass(homePage.this,TransactionRecord.class);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.item3){
                    Intent intent = new Intent();
                    intent.setClass(homePage.this,personalPage.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);

                    return true;
                }else if(id == R.id.item4){
                    Intent intent = new Intent();
                    intent.setClass(homePage.this, dailyIncAndLos.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);
                    return true;
                }else if(id == R.id.item5){
                    Intent intent = new Intent();
                    intent.setClass(homePage.this, PersonalStockRanking.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);
                    return true;
                }
                else if(id == R.id.item7){
                    Intent intent = new Intent();
                    intent.setClass(homePage.this, myfunds.class);
                    intent.putExtra("id",userName);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    /*private void initData(){
        IconBean Add = new IconBean("ADD",R.drawable.regi_background);
        mIconBeenList.add(Add);
        IconBean Close = new IconBean("Jayess",R.drawable.youxiang);
        mIconBeenList.add(Close);
        IconBean ZYZ = new IconBean("ZYZ",R.drawable.youxiang);
        mIconBeenList.add(ZYZ);
        IconBean ZX = new IconBean("ZX",R.drawable.youxiang);
        mIconBeenList.add(ZX);
        IconBean XBY = new IconBean("XBY",R.drawable.youxiang);
        mIconBeenList.add(XBY);
        IconBean JLXN = new IconBean("JLXN",R.drawable.youxiang);
        mIconBeenList.add(JLXN);
        IconBean WZDJ = new IconBean("WZDJ",R.drawable.youxiang);
        mIconBeenList.add(WZDJ);
    }*/
    /*async*/
    class GetNewsData extends AsyncTask<Void,Void,ArrayList<news>> {

        @Override
        protected ArrayList<news> doInBackground(Void... params) {
            News_provider b = new News_provider();
            ArrayList<news> list= new ArrayList<news>();
            try {
                String res = b.getNews();
                String result = "";
                JSONObject object = new JSONObject(res);
                // JSONObject object =  new JSONObject(result);
                //System.out.println(object.get("status"));
                //Log.i("*******", "res ：" + object.getString("status").toString());
                JSONArray articles = object.optJSONArray("articles");
                // Log.i("*******", "res1 ：" + articles);
                for (int i = 0; i < articles.length(); i++) {
                    JSONObject jsonObject = (JSONObject) articles.get(i);
                    String title = jsonObject.getString("title");
                    // Log.i("*******", "res1 ：" + title);
                    String news_url = jsonObject.getString("url");
                    // Log.i("*******", "res1 ：" + news_url);
                    String publishedAt = jsonObject.getString("publishedAt");
                    String content = jsonObject.getString("content");
                    String urlToImage=jsonObject.getString("urlToImage");
                    // news n = new news();
                    news n = new news(title, news_url, publishedAt, content,urlToImage);
                    // Log.i("********", "插入标题");
                    // n.setTitle(title);
                    list.add(n);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        @Override
        protected void onPostExecute(final ArrayList<news> newslist) {
            for (int i =0; i<newslist.size();i++){

                IconBean Add = new IconBean(newslist.get(i).getTitle(),newslist.get(i).getUrlToImage());
                /*IconBean Add = new IconBean(newslist.get(i).getTitle(),R.drawable.regi_background);*/
                Add.setNew_url(newslist.get(i).getNews_url());
                mIconBeenList.add(Add);




/*
                String title = newslist.get(i).getTitle();
                String url = newslist.get(i).getNews_url();
                String publishAt =  newslist.get(i).getPublishedAt();
                String content = newslist.get(i).getContent();
                String urlToImage = newslist.get(i).getUrlToImage();
                Log.i("***********","new1_title:"+title);
                Log.i("***********","new1_title:"+url);
                Log.i("***********","new1_title:"+publishAt);
                Log.i("***********","new1_title:"+content);
                Log.i("***********","new1_pic_url:"+ urlToImage );*/
            }
            lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    news a=newslist.get(position);
                    Intent intent=new Intent(homePage.this, new_detail_page.class);
                    intent.putExtra("n_url",a.getNews_url());
                    startActivity(intent);
                }
            });
           /* mAdapter=new stockAdapter(stock_list,mContext);
            list_stockview.setAdapter(mAdapter);
            list_stockview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    stocklist a=stock_list.get(position);
                    Intent intent=new Intent(Allstocks.this, stock_detail.class);
                    intent.putExtra("gid",a.getname());
                    startActivity(intent);

                }
            });*/

        }

    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(mToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
