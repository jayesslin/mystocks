package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.team3.ms.mystocks.Adapter.IconAdapter;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IconBean;

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
        initData();
        lv.setAdapter(new IconAdapter(homePage.this,mIconBeenList));

        Stock = (TextView)findViewById(R.id.Stock);
        Stock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ss11 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(ss11);
            }
        });
        /*
        search_bt = (ImageView)findViewById(R.id.search_bt);
        search_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss11 = new Intent(getApplicationContext(),stockList.class);
                startActivity(sss11);
            }
        });*/
    }
    private void initData(){
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
    }
}
