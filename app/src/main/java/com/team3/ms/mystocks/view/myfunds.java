package com.team3.ms.mystocks.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
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
    private ImageView search,backButton;
    private List<funds> fund_list=new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myfunds_page);
        fund_view = (ListView) findViewById(R.id.funds_list);
        search = (ImageView)findViewById(R.id.search_bt1);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent s1 = new Intent(getApplicationContext(),myfunds_search.class);

                startActivity(s1);
            }
        });
        backButton = (ImageView) findViewById(R.id.backH);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),homePage.class);
                startActivity(s1);
            }
        });



        fundslist fl = new fundslist();
        String[] PDFDX1 = {"green", "green", "green"};
        funds PDFDX = new funds("PDFDX", "$54.88", "41.16%", "27.83%", "8.09$", PDFDX1);


        //fund_list.add(PDFDX);
        fund_list=fl.getMy_list();
       /* for (funds s : fund_list) {
            Log.i("+++++++++", s.getFund_symbol());
        }*/
        mAdapter = new fundsadapter(fund_list, myfunds.this);
        fund_view.setAdapter(mAdapter);
        fund_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                funds a=fund_list.get(position);
                System.out.println("this"+a);
                Intent intent=new Intent(myfunds.this, fund_details.class);
                startActivity(intent);

            }
        });
    }
}



