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
import com.team3.ms.mystocks.entity.option;

import java.util.List;

public class optionAdapter extends BaseAdapter{


    private List<option> optionList;
    private Context mContext;

    public optionAdapter(List<option> optionList, Context mContext) {
        for(option a: optionList){

            Log.i("+++++适配器",a.getOption_symbol());
        }
        this.optionList = optionList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return optionList.size();
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.option_item, parent,false);

        TextView option_symbol = (TextView) convertView.findViewById(R.id.option_symbol);
        TextView option_type = (TextView) convertView.findViewById(R.id.option_type);
        TextView option_price= (TextView) convertView.findViewById(R.id.option_price);
        TextView option_position= (TextView) convertView.findViewById(R.id.option_position);
        TextView option_date= (TextView) convertView.findViewById(R.id.option_date);

        option_symbol.setText(optionList.get(position).getOption_symbol());
        option_type.setText(optionList.get(position).getOption_type());
        option_price.setText(optionList.get(position).getOption_price());
        option_position.setText(optionList.get(position).getOption_position());
        option_date.setText(optionList.get(position).getOption_date());

        return convertView;
    }
}
