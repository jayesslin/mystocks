package com.team3.ms.mystocks.tools;

import android.util.Log;

import com.team3.ms.mystocks.entity.news;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class ThreadforNet extends Thread {
    public static final String apikey = "235b46f0d14c4bd997654d3cbd52a2cd";
    String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apikey;//请求接口地址
    News_API a = new News_API();

    @Override
    public void run() {
        ArrayList<news> list = new ArrayList<news>();
        String result = null;
        //String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apikey;//请求接口地址
        try {
            result = a.net(url, "GET");
            JSONObject object = new JSONObject(result);
            // JSONObject object =  new JSONObject(result);
            //System.out.println(object.get("status"));
            Log.i("*******", "res ：" + object.getString("status").toString());
            JSONArray articles = object.optJSONArray("articles");
            Log.i("*******", "res1 ：" + articles);
            for (int i = 0 ; i<articles.length();i++){
                JSONObject jsonObject = (JSONObject) articles.get(i);
                String title = jsonObject.getString("title");
                Log.i("*******", "res1 ：" + title);
                String news_url = jsonObject.getString("url");
                //String news_url = jsonObject.getString("url");
                Log.i("*******", "res1 ：" + news_url);
                String publishedAt = jsonObject.getString("publishedAt");
                String content = jsonObject.getString("content");
                news n = new news(title,news_url,publishedAt,content);
                Log.i("*******", "news ：" + n.getNews());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
            /*if (object.getString("status").toString().equals("ok")) {

                JSONObject res = object.optJSONObject("totalResults");
                //JSONArray data = res.optJSONArray("data");
                Log.i("*******", "result ：" + res);
                Log.i("*******", "********");
                //Log.i("*******", "data ：" + data);
                //System.out.println(res);
               // System.out.println(data);

                System.out.println("===============");
                System.out.println("===============");
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
            }*/

    }
}