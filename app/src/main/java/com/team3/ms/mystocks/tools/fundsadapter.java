package com.team3.ms.mystocks.tools;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.funds;
import com.team3.ms.mystocks.entity.stocklist;

import java.util.List;

public class fundsadapter  extends BaseAdapter{


    private List<funds> fundList;
    private Context mContext;

    public fundsadapter(List<funds> fundList, Context mContext) {
        for(funds a: fundList){

        Log.i("+++++适配器",a.getFund_symbol());
        }
        this.fundList = fundList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return fundList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.funds_item, parent,false);

        TextView fund_sym = (TextView) convertView.findViewById(R.id.fundssym);
        TextView fund_nav = (TextView) convertView.findViewById(R.id.fund_nav);
        TextView fund_1y= (TextView) convertView.findViewById(R.id.fund_1y);
        TextView fund_3y= (TextView) convertView.findViewById(R.id.fund_3y);
        TextView fund_5y= (TextView) convertView.findViewById(R.id.fund_5y);

        fund_sym.setText(fundList.get(position).getFund_symbol());
        fund_nav.setText(fundList.get(position).getFund_nav());
        fund_1y.setText(fundList.get(position).getFund_1y());
        fund_3y.setText(fundList.get(position).getFund_3y());
        fund_5y.setText(fundList.get(position).getFund_5y());

        String[] colortmp = fundList.get(position).getColor();
        if(colortmp[0].equals("red")){
            fund_1y.setTextColor(android.graphics.Color.RED);
        }else{
            fund_1y.setTextColor(android.graphics.Color.GREEN);
        }
        if(colortmp[1].equals("red")){
            fund_3y.setTextColor(android.graphics.Color.RED);
        }else{
            fund_3y.setTextColor(android.graphics.Color.GREEN);
        }
        if(colortmp[2].equals("red")){
            fund_5y.setTextColor(android.graphics.Color.RED);
        }else{
            fund_5y.setTextColor(android.graphics.Color.GREEN);
        }
        return convertView;
    }
}