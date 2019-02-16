package com.team3.ms.mystocks.entity;

import java.util.ArrayList;

public class news {
   // private static ArrayList<news> newslist;
    private String title;
    private String news_url;
    private String publishedAt;
    private String content;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNews_url() {
        return news_url;
    }

    public void setNews_url(String news_url) {
        this.news_url =news_url;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public news(String title, String news_url,String publishedAt,String content){
        this.title=title;
        this.news_url=news_url;
        this.publishedAt=publishedAt;
        this.content=content;
    }
    public news(){

    }
    public String getNews(){
        return getTitle().toString()+getNews_url().toString()+getPublishedAt().toString()+getContent().toString();

    }
  /*  public void addnewslist(news n){
        newslist.add(n);
    }
    public ArrayList<news> getNewlist(){
        return  newslist;
    }
    public void reset_news_list(){
        newslist = null;
    }*/
}
