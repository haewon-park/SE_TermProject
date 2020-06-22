package com.example.se_termproject;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LibraryActivity extends AppCompatActivity {
    AlarmManager alarm_manager;
    PendingIntent pendingIntent;
    private Intent my_intent;
    private static final int REQUEST_CODE = 3333;
    public long calculateTime = 0;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private AlertDialog dialog;
    private boolean room = true;
    private String RoomNum;
    private Calendar calendar;
    private Spinner spinner;
    private Spinner time;
    private ArrayAdapter adapter;
    private ArrayAdapter adapter1;
    DocumentReference docRef;
    DocumentReference docRef2;
    // 현재시간을 msec 으로 구한다.
    long now = System.currentTimeMillis();
    // 현재시간을 date 변수에 저장한다.
    Date date = new Date(now);
    Calendar mCalendar = Calendar.getInstance();
    // 시간을 나타냇 포맷을 정한다 ( yyyy/MM/dd 같은 형태로 변형 가능 )
    SimpleDateFormat sdfNow = new SimpleDateFormat("HH:mm");
    // nowDate 변수에 값을 저장한다.
    String formatDate = sdfNow.format(date);
    TextView dateNow;
    String seatNum;
    String dept;
    String timeString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);


        docRef = db.collection("users").document(user.getUid());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        dept = bundle.getString("dept");
        seatNum = bundle.getString("seatNum");


        Log.d("test2",dept);
        Log.d("test2",seatNum);
        docRef2 = db.collection(dept).document(seatNum);

        final TextView timeText = (TextView) findViewById(R.id.timeTx);
        time = (Spinner) findViewById(R.id.spinner_time);

        adapter1 = ArrayAdapter.createFromResource(this, R.array.UseTime, android.R.layout.simple_spinner_dropdown_item);
        time.setAdapter(adapter1);

        dateNow = (TextView) findViewById(R.id.dateNow);
        dateNow.setText(formatDate);    // TextView 에 현재 시간 문자열 할당


        final Button registerButton = (Button) findViewById(R.id.roomButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String use_time = time.getSelectedItem().toString();

                int index = formatDate.indexOf(':');
                String hour = formatDate.substring(0, index);
                String minute = formatDate.substring(index + 1, formatDate.length());

                index = use_time.indexOf(':');
                String set_hour = use_time.substring(0, index);
                String set_minute = use_time.substring(index + 1, use_time.length());

                int current_hour = Integer.parseInt(hour);
                int current_minute = Integer.parseInt(minute);

                int added_hour = Integer.parseInt(set_hour);
                int added_minute = Integer.parseInt(set_minute);

                int hours = current_hour + added_hour;
                int minutes = current_minute + added_minute;

                if (seatNum.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LibraryActivity.this);
                    dialog = builder.setMessage("좌석을 입력해 주세요.")
                            .setPositiveButton("확인", null)
                            .create();
                    dialog.show();
                    return;
                } else {
                    /*좌석 상태 받아오기 int status=0*/
                    int status = 0;
                    if (status == 0) {
                        /*좌석 상태 바꾸기*/
                        double min;
                        if(added_minute==30){
                            min=0.5;
                        }else{
                            min=0;
                        }
                        double result=added_hour+min;
                        timeString=Double.toString(result);

                        Toast.makeText(LibraryActivity.this, "좌석발권에 성공했습니다.", Toast.LENGTH_LONG).show();
                        //알람시간 지정해주어야함

                        docRef
                                .update("time", timeString)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("update", "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("update", "Error updating document", e);
                                    }
                                });


                        docRef
                                .update("seat", seatNum)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("update", "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("update", "Error updating document", e);
                                    }
                                });
                        docRef
                                .update("using", dept)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("update", "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("update", "Error updating document", e);
                                    }
                                });
                        docRef2
                                .update("status", "1")
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d("update", "DocumentSnapshot successfully updated!");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("update", "Error updating document", e);
                                    }
                                });

                        alarm_manager = (AlarmManager) getSystemService(ALARM_SERVICE);

                        // Calendar 객체 생성
                        calendar = Calendar.getInstance();

                        // 알람리시버 intent 생성
                        my_intent = new Intent(getApplicationContext(), Alarm_Receiver.class);

                        // db 선언
                        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

                        calendar.set(Calendar.HOUR_OF_DAY, hours);
                        calendar.set(Calendar.MINUTE, minutes);
                        calendar.set(Calendar.SECOND, 0);

                        int getHourTimePicker = hours;
                        int getMinuteTimePicker = minutes;
                        String getHour=Integer.toString(getHourTimePicker);
                        String getMinute=Integer.toString(getMinuteTimePicker);

                        SharedPreferences.Editor editor = pref.edit();

                        // db에 추가
                        editor.putString("set_hour", getHour);
                        editor.putString("set_min", getMinute);
                        editor.commit();


                        calculateTime = calendar.getTimeInMillis();
                        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE, my_intent,
                                PendingIntent.FLAG_UPDATE_CURRENT);

                        if (Build.VERSION.SDK_INT < 23) {
                            // 19 이상
                            if (Build.VERSION.SDK_INT >= 19) {
                                alarm_manager.setExact(AlarmManager.RTC_WAKEUP, calculateTime, pendingIntent);

                            }
                            // 19 미만
                            else {
                                // pass
                                // 알람셋팅
                                alarm_manager.set(AlarmManager.RTC_WAKEUP, calculateTime, pendingIntent);
                            }
                            // 23 이상
                        } else {
                            alarm_manager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calculateTime, pendingIntent);
                        }
                    } else {
                        Toast.makeText(LibraryActivity.this, "좌석이 사용 중입니다.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
            }
        });

    }
}