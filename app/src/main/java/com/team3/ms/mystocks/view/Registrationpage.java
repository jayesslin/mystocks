package com.team3.ms.mystocks.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.controller.usercontroller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registrationpage extends AppCompatActivity {

    private dbmanage dbMgr;
    private EditText Username,Email,Password,Email1;
    private Button Return,Registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regi_page);
        dbMgr = new dbmanage(Registrationpage.this, "MyStocks.db", null, 1);
/*
        Username = (EditText)findViewById(R.id.Username);
        Email1 = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);
        Registration = (Button)findViewById(R.id.Submit);
        Registration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Email = Email1.getText().toString();
                String UserName = Username.getText().toString();
                String password = Password.getText().toString();
                usercontroller uc = new usercontroller();//实例化UserController类；
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(UserName)||TextUtils.isEmpty(password)){
                    Toast.makeText(MainActivity.this,"Form cannot be empty!!!",(int)2000).show();
                }
                else {
                    uc.registered(dbMgr,Email,UserName,password);

                    Username.setText("");
                    Email1.setText("");
                    Password.setText("");
                }
            }
        });*/

        Username = (EditText)findViewById(R.id.Username);
        Email = (EditText)findViewById(R.id.email);
        Password = (EditText)findViewById(R.id.password);

        Registration = (Button)findViewById(R.id.Registration);
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            //提交按键的功能
            public void onClick(View v) {
                String username = Username.getText().toString();
                String email = Email.getText().toString();
                String password = Password.getText().toString();

                 if (isEmail(email)){
                    if(isUsername(username)){
                        if(isPassword(password)){
                                Toast.makeText(Registrationpage.this,"Successfully!!",(int)3000).show();
                                usercontroller uc = new usercontroller();//实例化UserController类；
                                uc.registered(dbMgr,email,username,password);
                                //Toast.makeText(Regitest.this,"Successfully!",(int)2000).show();
                                Username.setText("");
                                Email.setText("");
                                Password.setText("");
                        }
                        else {
                            Toast.makeText(Registrationpage.this,"Your password is not correct!!",(int)3000).show();
                        }
                    }
                    else {
                        Toast.makeText(Registrationpage.this,"Your username is not correct!!",(int)3000).show();
                    }
                }
                else {
                    Toast.makeText(Registrationpage.this,"No such a emial,please check you have written the recipient's mail address correctly!",(int)3000).show();
                }

            }
        });

    }


    //邮箱验证
    public  boolean isEmail(String strEmail) {
        String strPattern = "^[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$";
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return  m.matches();

    }
    //用户名验证
    public  boolean isUsername(String username) {
        if(TextUtils.isEmpty(username)){
            return false;
        }
        else {
            return true;
        }
    }
    //密码验证
    public  boolean isPassword(String password) {
        if(TextUtils.isEmpty(password)){
            return false;
        }
        else {
            return true;
        }
    }




}
