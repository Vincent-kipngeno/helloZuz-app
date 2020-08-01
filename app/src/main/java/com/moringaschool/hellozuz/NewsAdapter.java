package com.moringaschool.hellozuz;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    private List<NewsData> mNews;
    private Typeface mTypeFace;
    public NewsAdapter (Context context, List<NewsData> news, Typeface typeFace){
        this.mContext = context;
        this.mNews = news;
        this.mTypeFace = typeFace;
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
        mViewHolder.imageView.setImageResource(mNews.get(position).getImgResId());
        mViewHolder.nameView.setTypeface(mTypeFace);
        return convertView;
    }
    private class MyViewHolder {
        TextView nameView, messageView;
        ImageView imageView;

        public MyViewHolder(View item) {
            imageView = (ImageView) item.findViewById(R.id.profilePic);
            nameView = (TextView) item.findViewById(R.id.name);
            messageView = (TextView) item.findViewById(R.id.newsContent);
        }
    }
}
