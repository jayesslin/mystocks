package com.team3.ms.mystocks.tools;

import android.os.AsyncTask;
import android.util.Log;

import com.team3.ms.mystocks.entity.stock;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class Stocks_provider {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    public static final String APPKEY ="f1f7b9a6e48b69f5fceb5e9f7fd0b968";
    //王鹏云APPKEY：f1f7b9a6e48b69f5fceb5e9f7fd0b968
    //219d99f4bb845ce7afde39c6de48ce1d


    //public static final String APPKEY ="b02affef6f4f8d0f1e639f407f9fe7b8";

    // 林道琰的APPKEY：fd8c893bb39a02710dae48722c0897cdefe67362

    public String getStock(String gid) throws Exception {
        String url ="http://web.juhe.cn:8080/finance/stock/usa";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("gid",gid);//股票代码，如：aapl 为“苹果公司”的股票代码
        params.put("key",APPKEY);//APP Key
        String res = null;
        res = net(url, params,"GET");
        return res;
    }



    public  String getRequest(String gid){
        String result =null;
        JSONObject object=null;
        String url ="http://web.juhe.cn:8080/finance/stock/usa";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("gid",gid);//股票代码，如：aapl 为“苹果公司”的股票代码
        params.put("key",APPKEY);//APP Key

        try {
            result =net(url, params, "GET");


            return result;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public  String getRequest5(int i) throws Exception{
        String result = null;

        String url = "http://web.juhe.cn:8080/finance/stock/usaall";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key", APPKEY);//您申请的APPKEY
        params.put("page",i);//第几页,每页20条数据,默认第1页

        result = net(url, params, "GET");


        return result;



           /* JSONObject object1=new JSONObject(result);
            System.out.print(object1);
            JSONArray jsonArray=object1.getJSONArray("gid");
            System.out.print(jsonArray);
            System.out.printf("test");*/
           // JSONObject mdata=(JSONObject) jsonArray.get(0);
           // for (int i=0;i<mdata.length();i++){
             //   JSONObject jsonObject= mdata.getJSONObject(i);

           // }


    }








    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
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
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
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
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) throws Exception{

        Stocks_provider sp=new Stocks_provider();

        System.out.print(sp.getRequest("aapl"));








    }

}


