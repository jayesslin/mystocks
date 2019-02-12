package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.entity.user;
import com.team3.ms.mystocks.controller.usercontroller;

import com.team3.ms.mystocks.R;

public class Login extends AppCompatActivity {
    private EditText accountEditText,passwordEditText;
    private Button loginButton,RegisteredButton,ChangeButton;
    private dbmanage dbMgr;
    public user user;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbMgr= new dbmanage(Login.this,"tm_tb",null,1);
        db=dbMgr.getWritableDatabase();
        accountEditText = (EditText)findViewById(R.id.accountEditText);
        passwordEditText = (EditText)findViewById(R.id.passwordEditText);
        loginButton = (Button)findViewById(R.id.loginButton);
        RegisteredButton = (Button)findViewById(R.id.RegisteredButton);
        ChangeButton = (Button)findViewById(R.id.ChangeButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = accountEditText.getText().toString();
                String pw = passwordEditText.getText().toString();
                usercontroller uc = new usercontroller();
                if (TextUtils.isEmpty(username)||TextUtils.isEmpty(pw)){
                    Toast.makeText(Login.this,"The account or password cannot be empty",(int)2000).show();
                }
                else if (uc.login(username,pw,dbMgr)){
//                    Intent s = new Intent(getApplicationContext(),MainEvent_Activity.class);
//                    s.putExtra("extra_data",username);
//                    s.putExtra("extra_password",pw);
//                    startActivity(s);
                }
                else {
                    Toast.makeText(Login.this,"WRONG PASSWORD!!!",(int)2000).show();

                }
            }
        });
//        RegisteredButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent s2 = new Intent(getApplicationContext(),Register_Activity.class);
//                startActivity(s2);
//            }
//        });
//        ChangeButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent s3 = new Intent(getApplicationContext(),Changepassword_Activity.class);
//                startActivity(s3);
//
//            }
//        });

    }
}
