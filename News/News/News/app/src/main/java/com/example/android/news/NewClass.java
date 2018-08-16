package com.example.android.news;


public class NewClass {
    private String mSection;
    private String mNews;
    private String mUrl;

    //constructor for news class having three inputs section,news and url

    public NewClass(String section, String news, String url){
        mSection=section;
        mNews=news;
        mUrl=url;
    }
    public String getmSection(){
        return mSection;
    }
    public String getmNews(){
        return mNews;
    }
    public String getmUrl(){
        return mUrl;
    }
}
