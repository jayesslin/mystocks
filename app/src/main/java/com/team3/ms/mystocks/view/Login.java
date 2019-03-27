package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.team3.ms.mystocks.DBmgr.dbmanage;
import com.team3.ms.mystocks.entity.news;
import com.team3.ms.mystocks.entity.user;
import com.team3.ms.mystocks.controller.usercontroller;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.tools.News_provider;

import java.util.ArrayList;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        dbMgr= new dbmanage(Login.this,"MyStocks.db",null,5);



         /*AsyncTask *//*test for news*/





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
                else if (uc.verifes(username,pw,dbMgr)){
                    System.out.println("hi");
                    Toast.makeText(Login.this,"Success",(int)2000).show();
                    Intent s = new Intent(getApplicationContext(), homePage.class);
                    s.putExtra("id",username);
//                    s.putExtra("extra_data",username);
//                    s.putExtra("extra_password",pw);
                    startActivity(s);
                }
                else {
                    System.out.println("hi");
                    Toast.makeText(Login.this,"Username or password is incorrect!!!",(int)2000).show();

                }
            }
        });
        RegisteredButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s2 = new Intent(getApplicationContext(),Registrationpage.class);
                startActivity(s2);
            }
        });
        ChangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s3 = new Intent(getApplicationContext(),Changepasswordpage.class);
                startActivity(s3);

            }
        });

    }

    /*async*/
    class GetNewsData extends AsyncTask<Void,Void,ArrayList<news>> {

        @Override
        protected ArrayList<news> doInBackground(Void... params) {
            News_provider b = new News_provider();
            ArrayList<news> list= new ArrayList<news>();
            try {
                String res = b.getNews();
                String result = "";
                JSONObject object = new JSONObject(res);
                JSONArray articles = object.optJSONArray("articles");

                for (int i = 0; i < articles.length(); i++) {
                    JSONObject jsonObject = (JSONObject) articles.get(i);
                    String title = jsonObject.getString("title");

                    String news_url = jsonObject.getString("url");

                    String publishedAt = jsonObject.getString("publishedAt");
                    String content = jsonObject.getString("content");
                    String urlToImage= jsonObject.getString("urlToImage");

                    news n = new news(title, news_url, publishedAt, content, urlToImage );

                    list.add(n);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }
        @Override
        protected void onPostExecute(ArrayList<news> newslist) {
            for (int i =0; i<newslist.size();i++){
                String title = newslist.get(i).getTitle();
                String url = newslist.get(i).getNews_url();
                String publishAt =  newslist.get(i).getPublishedAt();
                String content = newslist.get(i).getContent();
                Log.i("***********","new1_title:"+title);
                Log.i("***********","new1_title:"+url);
                Log.i("***********","new1_title:"+publishAt);
                Log.i("***********","new1_title:"+content);
            }
            accountEditText.setText(newslist.get(0).getTitle());
        }

    }


}
