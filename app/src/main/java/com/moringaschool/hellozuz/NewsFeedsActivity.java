package com.moringaschool.hellozuz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedsActivity extends AppCompatActivity implements View.OnClickListener {
    Context context = NewsFeedsActivity.this;
    List<NewsData> myNews = new ArrayList<>();
    String[] names = new String[] {
            "Vincent", "Ibrahimovic", "Kevin", "MUtali", "Uhuru", "Kariuki", "Kagwe", "Thaisons", "Thompsons",
            "Collins", "Fernandes"
    };
    String[] news = new String[] {
            "Vincent is here typing", "Ibrahimovic says hello to all", "What is today's topic of discussion guys", "MUtali is saying that we should remamber to keep time since, we still have to get back to classes and study to prepare for lessons and exams make sure you all keep time", "I will be talking with the school head so that he may consider giving us more time to discuss this topic. It seems too wide", "That's fine Uhuru", "Observe health guidelines also guys, take care not to be infected by Covid19", "No worries kagwe", "That is fine too",
            "I think you guys have exhausted everything, I have no issue", "Fernandes is also okay here."
    };
    @BindView(R.id.newsList) ListView mListView;
    @BindView(R.id.introText) TextView mIntroText;
    @BindView(R.id.directMessages) Button mDirectMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds2);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("user");
        mIntroText.setText(String.format("Welcome To Our NewsFeeds %s", userName));
        getNewsList();
        mListView.setAdapter(new NewsAdapter(context, myNews));
        mDirectMessages.setOnClickListener(this);
    }

    private  void getNewsList () {
        for (int i = 0; i < news.length; i++) {
            NewsData newsData = new NewsData(news[i], names[i]);
            myNews.add(newsData);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == mDirectMessages){
            Intent messageIntent = new Intent(context, MessageListActivity.class);
            startActivity(messageIntent);
        }
    }
}