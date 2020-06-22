package com.example.se_termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseUser;

public class AdminModeActivity extends AppCompatActivity {
    FirebaseUser user;
    SeatDTO seatDTO;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_mode);

        findViewById(R.id.centerButton).setOnClickListener(onClickListener);
        findViewById(R.id.elecInfoButton).setOnClickListener(onClickListener);
        findViewById(R.id.collegeButton).setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.centerButton:
                    intent = new Intent(getApplicationContext(), AdminCenterActivity.class);
                    startActivity(intent);
                    break;

            }
            switch (v.getId()) {
                case R.id.elecInfoButton:
                    intent = new Intent(getApplicationContext(), AdminElectroActivity.class);
                    startActivity(intent);
                    break;

            }
            switch (v.getId()) {
                case R.id.collegeButton:
                    intent = new Intent(getApplicationContext(), AdminCollegeActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
