package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.moringaschool.hellozuz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.directMessageButton) Button mDirectMessagesButton;
    @BindView(R.id.generalMessageButton) Button mGeneralMessagesButton;
    @BindView(R.id.flickrButton) Button mFlickrButton;
    @BindView(R.id.dashboardTitle) TextView mDashboardTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        String userName = getIntent().getStringExtra("user");
        mDashboardTitle.setText(String.format("Welcome to DashBoard %s !", userName));
        mDirectMessagesButton.setOnClickListener(this);
        mGeneralMessagesButton.setOnClickListener(this);
        mFlickrButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mDirectMessagesButton){
            Intent intent = new Intent (DashboardActivity.this, MessageListActivity.class);
            startActivity(intent);
        }
        if (view == mGeneralMessagesButton){
            Intent intent = new Intent (DashboardActivity.this, NewsFeedsActivity.class);
            startActivity(intent);
        }
        if (view == mFlickrButton){
            Intent intent = new Intent (DashboardActivity.this, PhotoCollectionsActivity.class);
            startActivity(intent);
        }
    }
}