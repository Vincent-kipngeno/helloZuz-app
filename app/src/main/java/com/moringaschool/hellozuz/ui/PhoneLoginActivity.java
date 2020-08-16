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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.moringaschool.hellozuz.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;

public class PhoneLoginActivity extends AppCompatActivity implements View.OnClickListener
{
    @BindView(R.id.send_ver_code_button) Button SendVerificationCodeButton;
    @BindView(R.id.verify_button) Button VerifyButton;
    @BindView(R.id.phone_nnumber_input) EditText InputPhoneNumber;
    @BindView(R.id.verification_code_input) EditText InputVerificationCode;

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks callbacks;
    private FirebaseAuth mAuth;

    private ProgressDialog loadingBar;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_login);


        mAuth = FirebaseAuth.getInstance();

        loadingBar = new ProgressDialog(this);


        SendVerificationCodeButton.setOnClickListener(this);


        VerifyButton.setOnClickListener(this);


        callbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential)
            {
                signInWithPhoneAuthCredential(phoneAuthCredential);
            }

            @Override
            public void onVerificationFailed(FirebaseException e)
            {
                loadingBar.dismiss();
                Toast.makeText(PhoneLoginActivity.this, "Invalid Phone Number, Please enter correct phone number with your country code...", Toast.LENGTH_SHORT).show();

                SendVerificationCodeButton.setVisibility(View.VISIBLE);
                InputPhoneNumber.setVisibility(View.VISIBLE);

                VerifyButton.setVisibility(View.INVISIBLE);
                InputVerificationCode.setVisibility(View.INVISIBLE);
            }

            public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken token)
            {
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;

                loadingBar.dismiss();
                Toast.makeText(PhoneLoginActivity.this, "Code has been sent, please check and verify...", Toast.LENGTH_SHORT).show();

                SendVerificationCodeButton.setVisibility(View.INVISIBLE);
                InputPhoneNumber.setVisibility(View.INVISIBLE);

                VerifyButton.setVisibility(View.VISIBLE);
                InputVerificationCode.setVisibility(View.VISIBLE);
            }
        };
    }


    @Override
    public void onClick(View view) {
        if (view == SendVerificationCodeButton){
            String phoneNumber = InputPhoneNumber.getText().toString();

            if (TextUtils.isEmpty(phoneNumber))
            {
                Toast.makeText(PhoneLoginActivity.this, "Please enter your phone number first...", Toast.LENGTH_SHORT).show();
                InputPhoneNumber.setError("Please enter your phone number first...");
            }
            else
            {
                loadingBar.setTitle("Phone Verification");
                loadingBar.setMessage("please wait, while we are authenticating your phone...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        phoneNumber,        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        PhoneLoginActivity.this,               // Activity (for callback binding)
                        callbacks);        // OnVerificationStateChangedCallbacks
            }
        }
        if (view == VerifyButton){
            SendVerificationCodeButton.setVisibility(View.INVISIBLE);
            InputPhoneNumber.setVisibility(View.INVISIBLE);

            String verificationCode = InputVerificationCode.getText().toString();

            if (TextUtils.isEmpty(verificationCode))
            {
                Toast.makeText(PhoneLoginActivity.this, "Please write verification code first...", Toast.LENGTH_SHORT).show();
                InputVerificationCode.setError("Please write verification code first...");
            }
            else
            {
                loadingBar.setTitle("Verification Code");
                loadingBar.setMessage("please wait, while we are verifying verification code...");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();

                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, verificationCode);
                signInWithPhoneAuthCredential(credential);
            }
        }
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            loadingBar.dismiss();
                            Toast.makeText(PhoneLoginActivity.this, "Congratulations, you're logged in successfully...", Toast.LENGTH_SHORT).show();
                            SendUserToDashboardActivity();
                        }
                        else
                        {
                            String message = task.getException().toString();
                            Toast.makeText(PhoneLoginActivity.this, "Error : "  +  message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }




    private void SendUserToDashboardActivity()
    {
        Intent mainIntent = new Intent(PhoneLoginActivity.this, DashboardActivity.class);
        startActivity(mainIntent);
        finish();
    }

}