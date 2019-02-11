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
public class Regitest extends AppCompatActivity {
    private EditText editText, editText2, editText3;
    private Button okButton, Back;
    private dbmanage dbMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regi_page);
        dbMgr = new dbmanage(Regitest.this, "MyStocks.db", null, 1);
        editText = (EditText)findViewById(R.id.Email);
        editText2 = (EditText)findViewById(R.id.UserName);
        editText3 = (EditText)findViewById(R.id.newPassword);
        okButton = (Button)findViewById(R.id.Submit);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String Email = editText.getText().toString();
                String UserName = editText2.getText().toString();
                String password = editText3.getText().toString();
                usercontroller uc = new usercontroller();//实例化UserController类；
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(UserName)||TextUtils.isEmpty(password)){
                    Toast.makeText(Regitest.this,"Form cannot be empty!!!",(int)2000).show();
                }
                else {
                    uc.registered(dbMgr,Email,UserName,password);
                    Toast.makeText(Regitest.this,"Successfully!",(int)2000).show();
                    editText.setText("");
                    editText2.setText("");
                    editText3.setText("");
                }
            }
        });
        Back = (Button)findViewById(R.id.Back);
            Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent sss2 = new Intent(getApplicationContext(),Login_Activity.class);
                //startActivity(sss2);
                Log.i("++++++++++++","Regicomplete");
            }
        });
}
}
