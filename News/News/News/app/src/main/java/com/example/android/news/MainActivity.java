package com.example.android.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<NewClass>>{

    String newsapi = "http://content.guardianapis.com/search?page-size=20&q=entertainment&api-key=test";
    private int loader_id=1;
    private NewsAdap mAdapter=null;
    private TextView mEmptyTextView=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsList = (ListView) findViewById(R.id.list);
        mEmptyTextView = (TextView) findViewById(R.id.emptyView);
        mAdapter = new NewsAdap(this, new ArrayList<NewClass>());

        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                NewClass currentNews = mAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent newsIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(newsIntent);
            }
        });
        android.app.LoaderManager loaderManager = null;

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            mEmptyTextView.setVisibility(View.GONE);
            loaderManager = getLoaderManager();
            loaderManager.initLoader(loader_id, null, MainActivity.this);
        } else {
            View loadingIndicator = findViewById(R.id.loadbar);
            loadingIndicator.setVisibility(View.INVISIBLE);
            mEmptyTextView.setText(getString(R.string.noCONNECTION));
        }
    }

    //class for loader
    @Override
    public Loader<List<NewClass>> onCreateLoader(int id, Bundle args) {
        return new NewsLoad(this, newsapi);
    }

    //onload finish method
    @Override
    public void onLoadFinished(Loader<List<NewClass>> loader, List<NewClass> data) {
        mEmptyTextView.setText(getString(R.string.loadingERROR));
        mAdapter.clear();
        ProgressBar progress = (ProgressBar) findViewById(R.id.loadbar);
        progress.setVisibility(View.GONE);
        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        } else mEmptyTextView.setVisibility(View.VISIBLE);
    }

    //onload reset method
    @Override
    public void onLoaderReset(Loader<List<NewClass>> loader) {
        mAdapter.clear();
    }
}
