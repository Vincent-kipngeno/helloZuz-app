package com.moringaschool.hellozuz.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.moringaschool.hellozuz.R;
import com.moringaschool.hellozuz.models.NewsData;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewsData> mNews;
    public NewsAdapter (Context context, List<NewsData> news){
        this.mContext = context;
        this.mNews = news;
    }
    @Override
    public int getCount() {
        return mNews.size();
    }

    @Override
    public NewsData getItem(int i) {
        return mNews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        MyViewHolder mViewHolder;
        if (convertView == null) {
            // get layout from xml file
            convertView = inflater.inflate(R.layout.news_adapter_activity, null);
            mViewHolder = new MyViewHolder(convertView);
            convertView.setTag(mViewHolder);

        } else {
            mViewHolder = (MyViewHolder) convertView.getTag();
        }
        mViewHolder.messageView.setText(mNews.get(position).getNews());
        mViewHolder.nameView.setText(mNews.get(position).getName());
        return convertView;
    }

    // create a view holder just to have clean refactored code to reduce complexity
    private class MyViewHolder {
        TextView nameView, messageView;

        public MyViewHolder(View item) {
            nameView = (TextView) item.findViewById(R.id.name);
            messageView = (TextView) item.findViewById(R.id.newsContent);
        }
    }
}
