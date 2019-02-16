package com.team3.ms.mystocks.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.news;
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.tools.News_provider;
import com.team3.ms.mystocks.tools.Stocks_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class homePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /*AsyncTask *//*test for stocks*/
        String st = "aapl";
        new GetStock().execute(st);
    }
    /*async*/
    class GetStock extends AsyncTask<String,Void,stock> {



        @Override
        protected stock doInBackground(String... params) {
            Stocks_provider sp = new Stocks_provider();
            stock stock = new stock();
            try {
                String res = sp.getStok(params[0]);
                String result = "";
                JSONObject object = new JSONObject(res);
                // JSONObject object =  new JSONObject(result);
                //System.out.println(object.get("status"));
                //Log.i("*******", "res ：" + object.getString("status").toString());
                JSONArray stock_result = object.optJSONArray("result");
                JSONObject jsonObject = (JSONObject) stock_result.get(0);
                JSONObject data = jsonObject.getJSONObject("data");
                String gid =data.getString("gid");
                String openpri =data.getString("openpri");
                String lastestpri =data.getString("lastestpri");
                String uppic =data.getString("uppic");
                String limit =data.getString("limit");
                return new stock(gid,openpri, lastestpri,uppic,limit);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return stock;
        }
        @Override
        protected void onPostExecute(stock st) {

                Log.i("***********","stock_gid:"+ st.getGid());
                Log.i("***********","new1_title:"+st.getOpenpri());
                Log.i("***********","new1_title:"+st.getLastestpri());
                Log.i("***********","new1_title:"+st.getColor());


        }

    }

}
