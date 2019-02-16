package com.team3.ms.mystocks.tools;

import android.util.Log;

import com.team3.ms.mystocks.entity.news;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class News_provider {
    public static final String apikey = "235b46f0d14c4bd997654d3cbd52a2cd";

    public String getNews() throws Exception {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apikey;//请求接口地址
        News_API a = new News_API();
        ArrayList<news> list = new ArrayList<news>();
        String res = null;
        res = a.net(url, "GET");
        return res;

        }

    }


