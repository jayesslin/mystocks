package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
    private ImageView search_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        // 定义 ListView组件
        lv=(ListView)findViewById(R.id.listView);
        //initData();
        //lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));
        new GetNewsData().execute();
        //lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));



/*        Stock = (TextView)findViewById(R.id.Stock);
        Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),stockList.class);
                startActivity(ss11);
            }
        });
        search_bt = (ImageView)findViewById(R.id.search_bt);
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss11 = new Intent(getApplicationContext(),stockList.class);
                startActivity(sss11);
            }
        });*/
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
        protected void onPostExecute(ArrayList<news> newslist) {
            for (int i =0; i<newslist.size();i++){

                IconBean Add = new IconBean(newslist.get(i).getTitle(),newslist.get(i).getUrlToImage());
                /*IconBean Add = new IconBean(newslist.get(i).getTitle(),R.drawable.regi_background);*/
                mIconBeenList.add(Add);





                String title = newslist.get(i).getTitle();
                String url = newslist.get(i).getNews_url();
                String publishAt =  newslist.get(i).getPublishedAt();
                String content = newslist.get(i).getContent();
                String urlToImage = newslist.get(i).getUrlToImage();
                Log.i("***********","new1_title:"+title);
                Log.i("***********","new1_title:"+url);
                Log.i("***********","new1_title:"+publishAt);
                Log.i("***********","new1_title:"+content);
                Log.i("***********","new1_pic_url:"+ urlToImage );
            }
            lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));
        }

    }
}
