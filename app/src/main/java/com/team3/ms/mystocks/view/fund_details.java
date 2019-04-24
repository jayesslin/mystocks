package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.team3.ms.mystocks.R;

public class fund_details extends AppCompatActivity {
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_details);
        backButton = (ImageView) findViewById(R.id.back_fu);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),fund_option.class);
                startActivity(s1);
            }
        });
    }
}
