package com.moringaschool.hellozuz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MessageListActivity extends AppCompatActivity {
    Context context = MessageListActivity.this;
    List<MessageData> myMessages = new ArrayList<>();
    String[] senders = new String[] {
            "Vincent", "Ibrahimovic", "Kevin", "MUtali", "Uhuru", "Kariuki", "Kagwe", "Thaisons", "Thompsons",
            "Collins", "Fernandes"
    };
    String[] messages = new String[] {
            "Vincent is here typing", "Ibrahimovic says hello to all", "What is today's topic of discussion guys", "MUtali is saying that we should remamber to keep time since, we still have to get back to classes and study to prepare for lessons and exams make sure you all keep time", "I will be talking with the school head so that he may consider giving us more time to discuss this topic. It seems too wide", "That's fine Uhuru", "Observe health guidelines also guys, take care not to be infected by Covid19", "No worries kagwe", "That is fine too",
            "I think you guys have exhausted everything, I have no issue", "Fernandes is also okay here."
    };

    @BindView(R.id.messageList) ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_list);
        ButterKnife.bind(this);

        getMessageList();
        mListView.setAdapter(new MessageAdapter(context, myMessages));
    }

    private  void getMessageList () {
        for (int i = 0; i < messages.length; i++) {
            MessageData messageData = new MessageData(messages[i], senders[i]);
            myMessages.add(messageData);
        }
    }
}