package com.moringaschool.hellozuz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsFeedsActivity extends AppCompatActivity {
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
    int[] imgResrces = new int[] {
            R.drawable.slack, R.drawable.slack, R.drawable.slack, R.drawable.slack, R.drawable.slack, R.drawable.slack, R.drawable.slack, R.drawable.slack,
            R.drawable.slack, R.drawable.slack
    };
    @BindView(R.id.newsList) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feeds2);
        ButterKnife.bind(this);

        getNewsList();
        mListView.setAdapter(new NewsAdapter(context, myNews, ));
    }

    private  void getNewsList () {
        for (int i = 0; i < news.length; i++) {
            NewsData newsData = new NewsData(news[i], names[1], imgResrces[1]);
            myNews.add(newsData);
        }
    }
}