package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.controller.usercontroller;


public class Changepasswordpage extends AppCompatActivity {

    //定义控件
    private EditText Username,email,password;
    private Button Submit,Return;
    private usercontroller uc=new usercontroller();
    private dbmanage dbMgr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.changepassword_page);

        //创建控件对象
        Username = (EditText)findViewById(R.id.Username);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);
        dbMgr = new dbmanage(Changepasswordpage.this, "MyStocks.db", null, 1);


        Submit = (Button)findViewById(R.id.Submit);
        Return = (Button)findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sss2 = new Intent(getApplicationContext(),Login.class);
                startActivity(sss2);
            }
        });

        //设置Submit按钮的功能
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = Username.getText().toString();
                String email_address = email.getText().toString();
                String psw = password.getText().toString();
                Log.i("*******","+"+username+"  :"+email_address+" "+psw);
                if(TextUtils.isEmpty(email_address)||TextUtils.isEmpty(username)||TextUtils.isEmpty(psw)){
                    Toast.makeText(Changepasswordpage.this,"Form cannot be empty!!!",(int)3000).show();
                }
                /*else if(uc.ChangePassword(dbMgr,username,email_address,psw)){
                    Log.i("*******","going insert!");
                    dbMgr.query();
                    Log.i("***********"," 0");
                    Toast.makeText(Changepasswordpage.this,"Successfully!",(int)2000).show();
                    //test
                    Username.setText("");
                    email.setText("");
                    password.setText("");
                    Intent sss4 = new Intent(getApplicationContext(),Login.class);
                    startActivity(sss4);
                }
                else {
                    Toast.makeText(Changepasswordpage.this,"ERROR EMAIL!",(int)2000).show();
                }*/
                else{
                   int res =  uc.ChangePassword(dbMgr,username,email_address,psw);
                   switch(res){
                        case 1 :
                            Log.i("*******","going insert!");
                            dbMgr.query();
                            Log.i("***********"," 0");
                            Toast.makeText(Changepasswordpage.this,"Successfully!",(int)2000).show();
                            //test
                            Username.setText("");
                            email.setText("");
                            password.setText("");
                            Intent sss4 = new Intent(getApplicationContext(),Login.class);
                            startActivity(sss4);
                            //语句
                            break;
                        case 2 :
                            Toast.makeText(Changepasswordpage.this,"Wrong Email!",(int)3000).show();
                            //语句
                            break;

                        case 3:
                            Toast.makeText(Changepasswordpage.this,"User is not exist!",(int)3000).show();//可选
                            break;
                    }
                }
            }
        });
    }
}
