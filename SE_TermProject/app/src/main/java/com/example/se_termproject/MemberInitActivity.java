package com.example.se_termproject;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MemberInitActivity extends AppCompatActivity {
    private static final String TAG = "MemberInitActivity";

    FirebaseUser user;
    UserInfo userInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_init);

        user = FirebaseAuth.getInstance().getCurrentUser();

        findViewById(R.id.checkButton).setOnClickListener(onClickListener);
    }

    // 뒤로 가기 버튼을 누르면 main activity 로 넘어가는 것을 막아줌
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        myStartActivity(SignUpActivity.class);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.checkButton:
                    profileUpdate();
                    break;

            }
        }
    };

    private void profileUpdate() {
        String name = ((EditText) findViewById(R.id.nameEditText)).getText().toString();
        String phoneNumber = ((EditText) findViewById(R.id.phoneNumberEditText)).getText().toString();
        String dept = ((EditText) findViewById(R.id.deptEditText)).getText().toString();
        String studentNumber = ((EditText) findViewById(R.id.studentNumberEditText)).getText().toString();

        if (name.length() > 0 && phoneNumber.length() > 9 && dept.length() > 5 && studentNumber.length() > 0) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            userInfo = new UserInfo(user.getUid(), name, phoneNumber, studentNumber, dept,"0","0");

            Log.d("testcase",userInfo.getDept());
            if (user != null) {
                db.collection("users").document(user.getUid()).set(userInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("회원정보 등록을 성공하였습니다.");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            public void onFailure(@NonNull Exception e) {
                                startToast("회원정보 등록에 실패하였습니다.");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }

        } else {
            startToast("회원정보를 입력해주세요.");
        }

        myStartActivity(ModeActivity.class);
    }

    private void startToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}