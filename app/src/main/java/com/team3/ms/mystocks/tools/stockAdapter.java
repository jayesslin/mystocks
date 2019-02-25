package com.team3.ms.mystocks.tools;
import android.content.Context;
import android.widget.BaseAdapter;

import java.util.LinkedList;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.stock;
import com.team3.ms.mystocks.entity.stocklist;

public class stockAdapter extends BaseAdapter {

    private List<stocklist> stockList;
    private Context mContext;

    public stockAdapter(List<stocklist> stockList, Context mContext) {
        this.stockList = stockList;
        this.mContext = mContext;
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
        txt_Lastpri.setText(stockList.get(position).getLastestpri());
        txt_limit.setText(stockList.get(position).getLimit());
        txt_symbol.setText(stockList.get(position).getname());

        return convertView;
    }
}