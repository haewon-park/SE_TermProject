package com.example.se_termproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class AdminTimeSetActivity extends AppCompatActivity {

    TextView timeText;
    Button plusButton;
    Button reduceButton;
    Button resetButton;
    String time;
    String result="";
    String hour="";
    String minute="";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_timeset);
        timeText = (TextView) findViewById(R.id.time);
        plusButton = (Button) findViewById(R.id.button1); //연장
        reduceButton = (Button) findViewById(R.id.button2); //감소
        resetButton = (Button) findViewById(R.id.button3); //리셋


        time = "18:00";

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminTimeSetActivity.this);

                builder.setMessage("1시간 연장합니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {



                        //time 값을 분할해서 각각 hour와 minutre에 저장장
                        String strArray[] = time.split(":");

                        hour = strArray[0];
                        minute = strArray[1];


                        int currentSetTime = Integer.parseInt(hour);


                        if(currentSetTime==23){
                            Toast.makeText(AdminTimeSetActivity.this, "더 이상 연장시킬 수 없습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        currentSetTime += 1;

                        result = Integer.toString(currentSetTime) + ":" + minute;

                        timeText.setText(result);
                        Toast.makeText(AdminTimeSetActivity.this, "1시간 연장되었습니다.", Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        reduceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminTimeSetActivity.this);

                builder.setMessage("1시간 감소시킵니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {





                        //time 값을 분할해서 각각 hour와 minutre에 저장장
                        String strArray[] = time.split(":");

                        hour = strArray[0];
                        minute = strArray[1];


                        int currentSetTime = Integer.parseInt(hour);
                        int currentSetMinute = Integer.parseInt(minute);

                        if(currentSetTime==1){
                            Toast.makeText(AdminTimeSetActivity.this, "더 이상 연장시킬 수 없습니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        currentSetTime -= 1;


                        result = Integer.toString(currentSetTime) + ":" + minute;

                        timeText.setText(result);
                        Toast.makeText(AdminTimeSetActivity.this, "1시간 감소되었습니다.", Toast.LENGTH_SHORT).show();
                        //1시간만큼 시간에서 빼주기

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminTimeSetActivity.this);

                builder.setMessage("리셋시킵니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        result = "18:00";

                        timeText.setText(result);
                        Toast.makeText(AdminTimeSetActivity.this, "리셋 되었습니다.", Toast.LENGTH_SHORT).show();
                        //1시간만큼 시간에서 빼주기

                    }
                });
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });


    }
}