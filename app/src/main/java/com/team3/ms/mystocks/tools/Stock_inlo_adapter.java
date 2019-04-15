package com.team3.ms.mystocks.tools;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IncomeLossObject;
import com.team3.ms.mystocks.entity.stocklist;

import java.util.List;



public class Stock_inlo_adapter  extends BaseAdapter {

    private List<stocklist> stockList;
    private Context mContext;
    private List<IncomeLossObject> inandloss;
    public Stock_inlo_adapter(List<stocklist> stockList, Context mContext,List<IncomeLossObject> inandloss) {
        this.stockList = stockList;
        this.mContext = mContext;
        this.inandloss= inandloss;
    }

    @Override
    public int getCount() {
        return stockList.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.stock_item, parent,false);

        TextView txt_title = (TextView) convertView.findViewById(R.id.stocktitle);
        TextView txt_Lastpri = (TextView) convertView.findViewById(R.id.stockLastpri);
        TextView txt_limit= (TextView) convertView.findViewById(R.id.stocklimit);
        TextView txt_symbol= (TextView) convertView.findViewById(R.id.stocksym);

        txt_title.setText(stockList.get(position).getGid());
        txt_Lastpri.setText(inandloss.get(position).getShares());
        txt_limit.setText(inandloss.get(position).getProfit());
        txt_symbol.setText(inandloss.get(position).getDate());
        if(stockList.get(position).getColor().equals("red")){
            txt_limit.setTextColor(android.graphics.Color.RED);
            txt_Lastpri.setTextColor(android.graphics.Color.RED);
        }else{
            txt_limit.setTextColor(Color.GREEN);
            txt_Lastpri.setTextColor(Color.GREEN);
        }

        return convertView;
    }
}