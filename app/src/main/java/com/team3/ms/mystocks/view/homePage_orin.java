package com.team3.ms.mystocks.view;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.tools.Stocks_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class homePage_orin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_orin_page);

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
                String res = sp.getStock(params[0]);
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
                String ustime =data.getString("ustime");
                return new stock(gid,openpri, lastestpri,uppic,limit,ustime);
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
                Log.i("***********","开盘价:"+st.getOpenpri());
                Log.i("***********","更新价格:"+st.getLastestpri());
                Log.i("***********","颜色："+st.getColor());
                Log.i("***********","时间："+st.getUstime());

        }

    }

}
