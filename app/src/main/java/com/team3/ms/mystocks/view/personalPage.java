package com.team3.ms.mystocks.view;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.ImageView;


import android.widget.TextView;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.user;
import com.team3.ms.mystocks.entity.user_info;

import android.support.v4.widget.SwipeRefreshLayout;

import java.util.List;

public class personalPage extends AppCompatActivity {
    private TextView Name,stockrecord;
    private TextView EmailAdress,text21,text17;
    private ImageView exit,back;
    private dbmanage dbMgr;
    private Context mContext;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private int loss,total;

    private void exitapp() {
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        dbMgr = new dbmanage(personalPage.this, "MyStocks.db", null, 1);
        Name = (TextView) findViewById(R.id.Name);
        text21 = (TextView) findViewById(R.id.textView21);
        text17 = (TextView) findViewById(R.id.textView17);
        EmailAdress = (TextView) findViewById(R.id.EmailAdress);
        exit = (ImageView) findViewById(R.id.exit);
        back=(ImageView) findViewById(R.id.backpersonal);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });
        stockrecord=(TextView) findViewById(R.id.textView24);
        stockrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(personalPage.this,TransactionRecord.class);
                startActivity(intent);

            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exitapp();

            }
        });
        Intent s1 = getIntent();
        String userName = s1.getStringExtra("id");//获得用户名
        Name.setText(userName);


        //通过用户名获取用户对象
        user user = dbMgr.getUser(userName);



        dbMgr= new dbmanage(personalPage.this,"MyStocks.db",null,1);
        Name = (TextView)findViewById(R.id.Name);
        EmailAdress = (TextView)findViewById(R.id.EmailAdress);

        //老方法 intent传值
 /*       Intent s1 =getIntent();
        String userName = s1.getStringExtra("id");//获得用户名*/
            /*  Name.setText(userName);
        //通过用户名获取用户对象
        user user = dbMgr.getUser(userName);*/

        //通过对象获取对象邮箱地址

        //单例模式
        user_info sd= user_info.getInstance();
        String username = sd.getId();
        Log.i("*************"," id 是"+ username);
        Name.setText(username);
        //通过用户名获取用户对象
        user user = dbMgr.getUser(username);
        String email_address = user.getEmail();
        Log.i("*************"," email 是"+ email_address);
        EmailAdress.setText(email_address);

        mSwipeRefreshLayout=(SwipeRefreshLayout) findViewById(R.id.swipe_ly);//初始化下拉刷新控件，
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loss=(int)((Math.random())*5);
                total=500-loss;
                text21.setText(""+loss);                   //刷新时要做的事情  
                text17.setText(""+total);
                mSwipeRefreshLayout.setRefreshing(false);//刷新完成          
                  }  });



    }
}
