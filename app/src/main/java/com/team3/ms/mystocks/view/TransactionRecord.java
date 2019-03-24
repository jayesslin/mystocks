package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.team3.ms.mystocks.R;
public class TransactionRecord extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactionrecord);
        ImageView backButton = (ImageView) findViewById(R.id.backH);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),homePage.class);
                startActivity(s1);
            }
        });
        ImageView AllstockButton = (ImageView) findViewById(R.id.stock_w);
        AllstockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),Allstocks.class);
                startActivity(s1);
            }
        });
    }
}
