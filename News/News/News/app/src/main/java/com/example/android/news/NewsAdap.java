package com.example.android.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


//custom news adapter
public class NewsAdap extends ArrayAdapter<NewClass> {
    public NewsAdap(Context context, List<NewClass> news) {
        super(context, 0,news);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.listitemid, parent, false);
        }
        NewClass currentNews = getItem(position);
        TextView section = (TextView) listItemView.findViewById(R.id.newsSection);
        section.setText(currentNews.getmSection());
        TextView news = (TextView) listItemView.findViewById(R.id.newsid);
        news.setText(currentNews.getmNews());
        return listItemView;
    }
}
