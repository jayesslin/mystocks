package com.team3.ms.mystocks.view;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.funds;
import com.team3.ms.mystocks.entity.fundslist;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;
import com.team3.ms.mystocks.tools.fundsadapter;
import com.team3.ms.mystocks.tools.stockAdapter;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class myfunds extends AppCompatActivity {
    private fundsadapter mAdapter=null;
    private ListView fund_view;
    private Context mContext;
    private Handler handler;
    private List<funds> fund_list=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfunds_page);
        fund_view = (ListView) findViewById(R.id.funds_list);


        fundslist fl = new fundslist();
        String[] PDFDX1 = {"green", "green", "green"};
        funds PDFDX = new funds("PDFDX", "$54.88", "41.16%", "27.83%", "8.09$", PDFDX1);


        //fund_list.add(PDFDX);
        fund_list=fl.getFund_list();
        for (funds s : fund_list) {
            Log.i("+++++++++", s.getFund_symbol());
        }
        mAdapter = new fundsadapter(fund_list, myfunds.this);
        fund_view.setAdapter(mAdapter);
    }
}
      /*  get();
        handler = new Handler() {
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    mAdapter = new fundsadapter(fund_list, myfunds.this);
                    fund_view.setAdapter(mAdapter);
                }
            }
        };
    }


            private void get() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            String[] PDFDX1 = {"green", "green", "green"};
                            funds PDFDX = new funds("PDFDX", "$54.88", "41.16%", "27.83%", "8.09$", PDFDX1);
                            fund_list.add(PDFDX);

                            for (funds s : fund_list) {
                                Log.i("+++++++++1", s.getFund_symbol());
                            }
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
*/



