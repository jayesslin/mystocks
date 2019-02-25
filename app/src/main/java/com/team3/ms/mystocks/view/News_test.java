package com.team3.ms.mystocks.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import com.team3.ms.mystocks.R;

public class News_test extends AppCompatActivity {
    private WebView webView;
    private static final String URL = "https://nypost.com/2019/02/16/islamic-states-caliphate-is-on-the-brink-of-defeat-in-syria/";

    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_home1_page);

        // 进行全屏

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_news_test);
        // 实例化WebView
        webView = (WebView) this.findViewById(R.id.wv_oauth);
        /**
         * 调用loadUrl()方法进行加载内容
         */
        webView.loadUrl(URL);
        /**
         * 设置WebView的属性，此时可以去执行JavaScript脚本
         */
        webView.getSettings().setJavaScriptEnabled(true);

        }
    }

