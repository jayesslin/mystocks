package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.team3.ms.mystocks.R;
public class TransactionRecord extends AppCompatActivity {

    private String[] data = {"03-21-2019                 FB                   +1000           164.34",
            "03-20-2019              AMZN                +300          1819.26",
            "03-18-2019               NFLX                 +500            358.78",
            "03-14-2019                NKE                  +900            86.80",
            "01-01-2019              AMZN                -800          1539.13",
            "08-28-2018              AMZN                +800         1998.10",
            "05-10-2018               NFLX               +1000          326.46"};
//    private String[] date = {"03-22-2019", "01-01-2019", "08-28-2018", "05-10-2018"};
//    private String[] stockID = {"FB", "AMZN", "AMZN", "NFLX"};
//    private String[] record = {"+1000", "-800", "+800", "+1000"};

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (
                TransactionRecord.this, android.R.layout.simple_list_item_1, data);
        ListView listView = (ListView) findViewById(R.id.list1);
        listView.setAdapter(adapter);
    }
}
