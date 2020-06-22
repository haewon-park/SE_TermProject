package com.example.se_termproject;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class ModeActivity extends AppCompatActivity {
    FirebaseUser user;
    SeatDTO seatDTO;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);


        findViewById(R.id.mobileButton).setOnClickListener(onClickListener);
        findViewById(R.id.roomButton).setOnClickListener(onClickListener);
        findViewById(R.id.infoLibraryButton).setOnClickListener(onClickListener);
        findViewById(R.id.readingRoomButton).setOnClickListener(onClickListener);
        findViewById(R.id.myButton).setOnClickListener(onClickListener);


    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.mobileButton:
                    intent = new Intent(getApplicationContext(), CenterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.roomButton:

                    break;
                case R.id.infoLibraryButton:

                    break;
                case R.id.readingRoomButton:
                    intent = new Intent(getApplicationContext(), MyActivity.class);
                    startActivity(intent);
                    break;
                case R.id.myButton:
                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://lib.gachon.ac.kr"));
                    startActivity(intent);
                    break;
            }
        }
    };
    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);

        startActivity(intent);
    }
}
