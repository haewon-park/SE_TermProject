package com.example.se_termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.perf.FirebasePerformance;
import com.google.firebase.perf.metrics.Trace;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;
    Trace myTrace;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        myTrace = FirebasePerformance.getInstance().newTrace("user login");

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.loginButton).setOnClickListener(onClickListener);
        findViewById(R.id.registerButton).setOnClickListener(onClickListener);
        findViewById(R.id.adminButton).setOnClickListener(onClickListener);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)  and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.loginButton:
                    myTrace.start();
                    signUp();
                    break;
                case R.id.registerButton:
                    Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                    startActivity(intent);
                    break;
                case R.id.adminButton:
                    Intent intent1 = new Intent(getApplicationContext(), AdminLoginActivity.class);
                    startActivity(intent1);
                    break;
            }
        }
    };

    private void signUp() {
        String email = ((EditText)findViewById(R.id.emailEditText)).getText().toString();
        String password = ((EditText)findViewById(R.id.passwordEditText)).getText().toString();

        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.METHOD, email);
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SIGN_UP, bundle);

        if(email.length() > 0 && password.length() > 0){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하였습니다.");
                                myTrace.stop();
                                startMainActivity();
                            } else {
                                if(task.getException() != null){
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
        }else {
            startToast("이메일 또는 비밀번호를 입력해 주세요.");
        }
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}