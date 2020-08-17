package com.moringaschool.hellozuz.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.hellozuz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.directMessageButton) Button mDirectMessagesButton;
    @BindView(R.id.generalMessageButton) Button mGeneralMessagesButton;
    @BindView(R.id.flickrButton) Button mFlickrButton;
    @BindView(R.id.dashboardTitle) TextView mDashboardTitle;

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private DatabaseReference RootRef;
    private String currentUserID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        currentUserID = mAuth.getCurrentUser().getUid();
        RootRef = FirebaseDatabase.getInstance().getReference();

        String userName = getIntent().getStringExtra("user");
        mDashboardTitle.setText(String.format("Welcome to the DashBoard %s !", userName));
        mDirectMessagesButton.setOnClickListener(this);
        mGeneralMessagesButton.setOnClickListener(this);
        mFlickrButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mDirectMessagesButton){
            Intent intent = new Intent (DashboardActivity.this, MainActivity.class);
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

    @Override
    protected void onStart()
    {
        super.onStart();

        if (currentUser == null)
        {
            sendUserToLoginActivity();
        }
        else {
            verifyUserExistance();
        }
    }

    private void verifyUserExistance()
    {
        String currentUserID = mAuth.getCurrentUser().getUid();

        RootRef.child("Users").child(currentUserID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                if ((dataSnapshot.child("name").exists()))
                {
                    Toast.makeText(DashboardActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    sendUserToSettingsActivity();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void sendUserToSettingsActivity()
    {
        Intent settingsIntent = new Intent(DashboardActivity.this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    private void sendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(DashboardActivity.this, LoginActivity.class);
        loginIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);

    }
}