package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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
    @BindView(R.id.password) EditText mPassword;

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
            String password = mPassword.getText().toString();

            //Checking if the user submits an empty form
            if (userName.trim().isEmpty() && password.trim().isEmpty()) {
                mUserName.setText("");
                mUserName.setHint("This Field is Required !!!");
                mUserName.setHintTextColor(Color.RED);
                mPassword.setText("");
                mPassword.setHint("This Field is Required !!!");
                mPassword.setHintTextColor(Color.RED);
            } else if (userName.trim().isEmpty()){
                mUserName.setText("");
                mUserName.setHint("This Field is Required !!!");
                mUserName.setHintTextColor(Color.RED);
            } else if (password.trim().isEmpty()){
                mPassword.setText("");
                mPassword.setHint("This Field is Required !!!");
                mPassword.setHintTextColor(Color.RED);
            } else {
                Log.e("MainActivity", "I have been reached");
                Intent intent = new Intent (MainActivity.this, DashboardActivity.class);
                intent.putExtra("user", userName);
                startActivity(intent);
                Log.d("MainActivity", "newsFeeds started");
            }
        }
    }
}