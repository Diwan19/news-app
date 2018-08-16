package com.example.android.news;

import android.content.Context;
import android.content.AsyncTaskLoader;

import java.util.List;


public class NewsLoad extends AsyncTaskLoader<List<NewClass>>{
    private String mUrl;

    public NewsLoad(Context context, String url){
        super(context);
        mUrl=url;
    }

    //method called on loading of the p
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    //loadin background method
    @Override
    public List<NewClass> loadInBackground() {
        if(mUrl==null){
            return null;
        }
        return Utils.fetchingNewsInfo(mUrl);
    }
}
