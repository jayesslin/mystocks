package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.user;

public class personalPage extends AppCompatActivity {
    private TextView Name;
    private TextView EmailAdress;
    private dbmanage dbMgr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);
        dbMgr= new dbmanage(personalPage.this,"MyStocks.db",null,1);
        Name = (TextView)findViewById(R.id.Name);
        EmailAdress = (TextView)findViewById(R.id.EmailAdress);
        Intent s1 =getIntent();
        String userName = s1.getStringExtra("id");//获得用户名
        Name.setText(userName);
        //通过用户名获取用户对象
        user user = dbMgr.getUser(userName);
        //通过对象获取对象邮箱地址
        String email_address = user.getEmail();
        EmailAdress.setText(email_address);
    }
}
