package com.team3.ms.mystocks.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.team3.ms.mystocks.R;
import com.team3.ms.mystocks.entity.option;
import com.team3.ms.mystocks.entity.optionList;
import com.team3.ms.mystocks.tools.optionAdapter;

import java.util.ArrayList;
import java.util.List;

public class fund_option extends AppCompatActivity {
    private ImageView backButton;
    private ViewPager fp_viewpaper;
    private ArrayList<View> fp_List;
    private optionAdapter mAdapter=null;
    private List<option> option_list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_option);
        backButton = (ImageView) findViewById(R.id.back_op);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent s1 = new Intent(getApplicationContext(),homePage.class);
                startActivity(s1);
            }
        });
        fp_viewpaper = (ViewPager) findViewById(R.id.viewpaper_fp);
        fp_List = new ArrayList<View>();
        LayoutInflater li = getLayoutInflater();
        fp_List.add(li.inflate(R.layout.activity_fund_list,null,false));
        View view_option = li.inflate(R.layout.activity_option_list,null,false);
        fp_List.add(view_option);
//        fp_List.add(li.inflate(R.layout.activity_option_list,null,false));
        fp_viewpaper.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                //这个方法是返回总共有几个滑动的页面（）
                return fp_List.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                //该方法判断是否由该对象生成界面。
                return view==object;
            }
            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                //这个方法返回一个对象，该对象表明PagerAapter选择哪个对象放在当前的ViewPager中。这里我们返回当前的页面
                fp_viewpaper.addView(fp_List.get(position));
                return fp_List.get(position);
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //这个方法从viewPager中移动当前的view。（划过的时候）
                fp_viewpaper.removeView(fp_List.get(position));
            }

        });
        TabLayout tabLayout= (TabLayout) findViewById(R.id.id_tl);
        tabLayout.setupWithViewPager(fp_viewpaper);
        tabLayout.getTabAt(0).setText("Funds");
        tabLayout.getTabAt(1).setText("Option");
        ListView option_l = (ListView) view_option.findViewById(R.id.op_list);
        optionList ol = new optionList();
        option_list=ol.getOption_list();
        mAdapter = new optionAdapter(option_list, fund_option.this);
        option_l.setAdapter(mAdapter);
        option_l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                option a=option_list.get(position);
                System.out.println("this"+a);
                Intent intent=new Intent(fund_option.this, option_detail.class);
                startActivity(intent);

            }
        });


    }
}
