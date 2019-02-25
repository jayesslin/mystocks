package com.team3.ms.mystocks.entity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.team3.ms.mystocks.R;

import java.util.List;

public class IconAdapter extends BaseAdapter {
    public List<IconBean> mlist;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView=mLayoutInflater.inflate(R.layout.lv_item,null);
            viewHolder.mImageView=(ImageView) convertView.findViewById(R.id.iv_icon);
            viewHolder.mTextView=(TextView) convertView.findViewById(R.id.lv_name);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //从list取出对象
        IconBean bean=mlist.get(position);
        //设置item的内容
        viewHolder.mImageView.setImageResource(bean.getIcon_Image_Id());
        viewHolder.mTextView.setText(bean.getIconName());
        return convertView;
    }

    private static class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
    }
    public IconAdapter(Context context,List<IconBean> list){
        mContext=context;
        mlist=list;
        mLayoutInflater=LayoutInflater.from(context);
    }
}
