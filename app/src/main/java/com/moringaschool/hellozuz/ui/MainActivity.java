package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.moringaschool.hellozuz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.userName) EditText mUserName;
    @BindView(R.id.loginButton) Button mLoginButton;
    @BindView(R.id.aboutText) TextView mAboutText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mLoginButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if (view == mLoginButton) {
            String userName = mUserName.getText().toString();
            Log.e("MainActivity", "I have been reached");
            Intent intent = new Intent (MainActivity.this, DashboardActivity.class);
            intent.putExtra("user", userName);
            startActivity(intent);
            Log.d("MainActivity", "newsFeeds started");
        }
    }
}