package com.moringaschool.hellozuz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;

public class NewsFeedsActivity extends AppCompatActivity {
    @BindView(R.id.newsList) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds2);

    }
}