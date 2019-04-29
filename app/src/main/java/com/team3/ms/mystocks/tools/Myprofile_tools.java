package com.team3.ms.mystocks.tools;

import android.os.Handler;
import android.os.Message;

import com.team3.ms.mystocks.entity.stocklist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Myprofile_tools {


    public void get( final Handler handler,final List<stocklist> stock_list) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Stocks_provider sp = new Stocks_provider();
                    String result = sp.getStocklist();
                    JSONArray array1 = new JSONArray(result);
                    for (int i = 0; i < 10; i++) {
                        JSONObject a = array1.getJSONObject(i);

                        String gid = a.getString("symbol");
                        String openpri = a.getString("open");
                        String lastestpri = a.getString("latestPrice");
                        String uppic = a.getString("high");
                        String limit = a.getString("change");
                        stocklist stock = new stocklist(gid, openpri, lastestpri, limit);
                        stock_list.add(stock);
                    }
                    Message msg = new Message();
                    msg.what = 1;
                    handler.sendMessage(msg);


                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
