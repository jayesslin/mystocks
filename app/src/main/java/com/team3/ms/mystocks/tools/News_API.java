package com.team3.ms.mystocks.tools;
import com.team3.ms.mystocks.entity.news;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import net.sf.json.JSONObject;
public class News_API {

    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final String apikey="235b46f0d14c4bd997654d3cbd52a2cd";

    public static ArrayList<news> getNews(){
        ArrayList<news> list = new ArrayList<news>();
        String result =null;
        String url ="https://newsapi.org/v2/top-headlines?country=us&apiKey="+apikey;//请求接口地址
        try {
            result =net(url,  "GET");
            JSONObject object = JSONObject.fromObject(result);
            //System.out.println(object.get("status"));
            if(object.getString("status").toString().equals("ok")){
                for (int i=0;i<object.getJSONArray("articles").size();i++) {
                    news n = new news();
                   // System.out.println("The "+i+" news");
                    n.setTitle((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("title")).toString());
                   // System.out.println((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("title")));

                    n.setUrl((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("url")).toString());
                   // System.out.println((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("url")));

                    n.setPublishedAt((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("publishedAt")).toString());
                   // System.out.println((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("publishedAt")));

                    n.setContent((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("content")).toString().toString());
                   // System.out.println((JSONObject.fromObject(object.getJSONArray("articles").get(i)).get("content")).toString());
                    list.add(n);
                    n=null;
                }
                //System.out.println(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("name"));
                //System.out.println(object.get("result").get(0).get("data"));
                System.out.println("===============");
                System.out.println("===============");
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public static String net(String strUrl, /*Map params,*/String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl;
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        News_API c = new News_API();
        ArrayList<news> res = new ArrayList<>();
        res = c.getNews();
        for(int i=0;i<res.size();i++){
            System.out.println(res.get(i).getTitle());
            System.out.println(res.get(i).getUrl());
            System.out.println(res.get(i).getPublishedAt());
            System.out.println(res.get(i).getContent());
            System.out.println("=================");
            System.out.println("=================");
        }
    }

}
