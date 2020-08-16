package com.moringaschool.hellozuz.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.moringaschool.hellozuz.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener
{
    @BindView(R.id.register_button) Button mCreateAccountButton;
    @BindView(R.id.register_email) EditText mUserEmail;
    @BindView(R.id.register_password) EditText mUserPassword;
    @BindView(R.id.already_have_account_link) TextView mAlreadyHaveAccountLink;

    private FirebaseAuth mAuth;
    private DatabaseReference mRootRef;

    private ProgressDialog loadingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mRootRef = FirebaseDatabase.getInstance().getReference();

        loadingBar = new ProgressDialog(this);


        mAlreadyHaveAccountLink.setOnClickListener(this);


        mCreateAccountButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mAlreadyHaveAccountLink){
            SendUserToLoginActivity();
        }
        if (view == mCreateAccountButton) {
            CreateNewAccount();
        }
    }


    private void CreateNewAccount()
    {
        String email = mUserEmail.getText().toString();
        String password = mUserPassword.getText().toString();

        if (TextUtils.isEmpty(email))
        {
            Toast.makeText(this, "Please enter email...", Toast.LENGTH_SHORT).show();
            mUserEmail.setError("Please enter email...");
        }
        if (TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Please enter password...", Toast.LENGTH_SHORT).show();
            mUserPassword.setError("Please enter password...");
        }
        else
        {
            loadingBar.setTitle("Creating New Account");
            loadingBar.setMessage("Please wait, while we wre creating new account for you...");
            loadingBar.setCanceledOnTouchOutside(true);
            loadingBar.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task)
                        {
                            if (task.isSuccessful())
                            {
                                String deviceToken = FirebaseInstanceId.getInstance().getToken();

                                String currentUserID = mAuth.getCurrentUser().getUid();
                                mRootRef.child("Users").child(currentUserID).setValue("");


                                mRootRef.child("Users").child(currentUserID).child("device_token")
                                        .setValue(deviceToken);

                                SendUserToDashboardActivity();
                                Toast.makeText(RegisterActivity.this, "Account Created Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                            else
                            {
                                String message = task.getException().toString();
                                Toast.makeText(RegisterActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();
                            }
                        }
                    });
        }
    }


    private void SendUserToLoginActivity()
    {
        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(loginIntent);
    }


    private void SendUserToDashboardActivity()
    {
        Intent mainIntent = new Intent(RegisterActivity.this, DashboardActivity.class);
        mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(mainIntent);
        finish();
    }

}