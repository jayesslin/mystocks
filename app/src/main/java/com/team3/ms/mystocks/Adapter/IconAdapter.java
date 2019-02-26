package com.team3.ms.mystocks.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.IconBean;
import com.team3.ms.mystocks.tools.GetImageByUrl;

import java.util.List;

public class IconAdapter extends BaseAdapter {

    public List<IconBean> mlist;
    public Context mContext;
    public LayoutInflater mLayoutInflater;
    @Override
    public int getCount() {
        int number = mlist.size();
        return number;
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
        //viewHolder.mImageView.setImageResource(bean.getIcon_Image_Id());

        // 这里调用了图片加载工具类的setImage方法将图片直接显示到控件上
        GetImageByUrl getImageByUrl = new GetImageByUrl();
        getImageByUrl.setImage(viewHolder.mImageView, bean.getIcon_Image_Id());

        viewHolder.mTextView.setText(bean.getIconName());
        return convertView;
    }

    private static class ViewHolder{
        public ImageView mImageView;
        public TextView mTextView;
    }

    public IconAdapter(Context context, List<IconBean> list){
        this.mContext = context;
        this.mlist = list;
        mLayoutInflater=LayoutInflater.from(context);
    }
}
