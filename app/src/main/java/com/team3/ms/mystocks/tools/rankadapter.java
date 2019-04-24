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
import com.team3.ms.mystocks.entity.IncomeLossObject;
import com.team3.ms.mystocks.entity.stocklist;

import java.math.BigDecimal;
import java.util.List;


public class rankadapter extends BaseAdapter {

    private List<stocklist> stockList;
    private Context mContext;
    private List<IncomeLossObject> inandloss;
    public rankadapter(List<stocklist> stockList, Context mContext, List<IncomeLossObject> inandloss) {
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
        TextView Sell= (TextView) convertView.findViewById(R.id.Sell);
        txt_title.setText(stockList.get(position).getGid());
        Sell.setText("Sell");

        Log.v("positionnnnnnnnnnnnnn:", position+"   "+stockList.get(position).getGid());
        if(stockList.get(position).getGid().equals("BABA")){
            txt_Lastpri.setText("100");
            txt_symbol.setText("03-24-2019");
            Double tmp = 100*(Double.parseDouble(stockList.get(position).getLastestpri())-Double.parseDouble(stockList.get(position).getOpenpri()));
            BigDecimal temp_b   =   new   BigDecimal(tmp);
            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            txt_limit.setText(profit+"");
        }else if(stockList.get(position).getGid().equals("FB")){
            txt_Lastpri.setText("20");
//            txt_limit.setText(inandloss.get(2).getProfit());
            txt_symbol.setText("04-02-2019");
            Double tmp = 20*(Double.parseDouble(stockList.get(position).getLastestpri())-Double.parseDouble(stockList.get(position).getOpenpri()));
            BigDecimal temp_b   =   new   BigDecimal(tmp);
            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            txt_limit.setText(profit+"");
        }else if(stockList.get(position).getGid().equals("AMZN")){
            txt_Lastpri.setText("30");
//            txt_limit.setText(inandloss.get(4).getProfit());
            txt_symbol.setText("04-01-2019");
            Double tmp = 30*(Double.parseDouble(stockList.get(position).getLastestpri())-Double.parseDouble(stockList.get(position).getOpenpri()));
            BigDecimal temp_b   =   new   BigDecimal(tmp);
            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            txt_limit.setText(profit+"");
        }else if(stockList.get(position).getGid().equals("AAPL")){
            txt_Lastpri.setText("50");
//            txt_limit.setText(inandloss.get(1).getProfit());
            txt_symbol.setText("03-16-2019");
            Double tmp = 50*(Double.parseDouble(stockList.get(position).getLastestpri())-Double.parseDouble(stockList.get(position).getOpenpri()));
            BigDecimal temp_b   =   new   BigDecimal(tmp);
            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            txt_limit.setText(profit+"");
        }else if(stockList.get(position).getGid().equals("PEP")){
            txt_Lastpri.setText("200");
//            txt_limit.setText(inandloss.get(3).getProfit());
            txt_symbol.setText("02-15-2019");
            Double tmp = 200*(Double.parseDouble(stockList.get(position).getLastestpri())-Double.parseDouble(stockList.get(position).getOpenpri()));
            BigDecimal temp_b   =   new   BigDecimal(tmp);
            double   profit   =   temp_b.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue();
            txt_limit.setText(profit+"");
        }


        if(stockList.get(position).getColor().equals("red")){
            txt_limit.setTextColor(Color.RED);
            txt_Lastpri.setTextColor(Color.RED);
        }else{
            txt_limit.setTextColor(Color.GREEN);
            txt_Lastpri.setTextColor(Color.GREEN);
        }

        return convertView;
    }
}