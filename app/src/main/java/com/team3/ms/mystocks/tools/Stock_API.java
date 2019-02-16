/*
package com.team3.ms.mystocks.tools;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.team3.ms.mystocks.entity.stock;

public class Stock_API {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static final String APPKEY ="b02affef6f4f8d0f1e639f407f9fe7b8";
   // public static final String gid="aapl";

    //美国股市
    public static stock getStock(String gid){
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/usa";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("gid",gid);//股票代码，如：aapl 为“苹果公司”的股票代码
        params.put("key",APPKEY);//APP Key

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
                System.out.println(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("name"));
                stock sk = new stock();
                sk.setGid(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("gid").toString());
                sk.setOpenpri(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("openpri").toString());
                sk.setLastestpri(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("lastestpri").toString());
                sk.setUppic(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("uppic").toString());
                sk.setLimit(JSONObject.fromObject(JSONObject.fromObject(object.getJSONArray("result").get(0)).get("data")).get("limit").toString());
                //System.out.println(object.get("result").get(0).get("data"));
                return sk;

            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //美国股市列表
    public static ArrayList<stock> getStockList(){
        ArrayList<stock> list  = new ArrayList<stock>();
        String result =null;
        String url ="http://web.juhe.cn:8080/finance/stock/usaall";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//您申请的APPKEY
        params.put("page","2");//第几页,每页20条数据,默认第1页

        try {
            result =net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
               // System.out.println(object.get("result"));
                for(int i =0;i<20;i++){
                    //System.out.println(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("symbol"));
                    stock sk= new stock();
                    sk.setGid(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("symbol").toString());
                    sk.setOpenpri(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("open").toString());
                    sk.setLastestpri(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("price").toString());
                    sk.setUppic(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("diff").toString());
                    sk.setLimit(JSONObject.fromObject(JSONObject.fromObject(object.get("result")).getJSONArray("data").get(i)).get("chg").toString());
                    list.add(sk);
                    sk= null;
                }
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
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

    //将map型转为请求参数型
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


    public static void main(String[] args) {
        //解析单照顾单只股票
        Stock_API c = new Stock_API();
        stock res = c.getStock("aapl");
        if(res!= null) {
            System.out.println("+++++++++++++");
            System.out.println(res.getGid());
            System.out.println(res.getOpenpri());
            System.out.println(res.getLastestpri());
            System.out.println(res.getUppic());
            System.out.println(res.getLimit());
            System.out.println(res.getColor());
        }else {
            System.out.println("null");
        }
        //解析股票列表
        Stock_API d = new Stock_API();
        ArrayList<stock> res = d.getStockList();
        for(int i =0;i<res.size();i++){
            System.out.println(res.get(i).getGid());
            System.out.println(res.get(i).getOpenpri());
            System.out.println(res.get(i).getLastestpri());
            System.out.println(res.get(i).getLimit());
            System.out.println(res.get(i).getColor());
            System.out.println("=================");
            System.out.println("=================");
        }
    }

}
*/
