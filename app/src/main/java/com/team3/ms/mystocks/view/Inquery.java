package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stocklist;
import com.team3.ms.mystocks.tools.Stocks_provider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Inquery extends AppCompatActivity {
    private Button goButton;
    private EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inquery);
        searchText = (EditText)findViewById(R.id.symbol);
        goButton = (Button)findViewById(R.id.go);
        goButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String symbol = searchText.getText().toString();
                Intent s1 = new Intent(getApplicationContext(),search_res.class);
                s1.putExtra("symbol",symbol);
                startActivity(s1);
            }
        });

    }


}
