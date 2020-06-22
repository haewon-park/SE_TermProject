package com.example.se_termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.Map;


// 사용시간이 끝나면 실행되는 Activity

public class Forced_Return extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    FirebaseFirestore db;
    DocumentReference docRefUserInfo;
    String time;
    String seatNum;
    String using;
    DocumentReference docRef;
    DocumentReference docRef2;


    AlarmManager alarm_manager;
    private Calendar calendar;
    PendingIntent pendingIntent;
    private Intent my_intent;
    private static final int REQUEST_CODE = 3333;
    public long calculateTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON| WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);


        FirebaseFirestore db = FirebaseFirestore.getInstance();
        docRefUserInfo = db.collection("users").document(user.getUid());


        // Initialize Firebase Auth
        docRefUserInfo.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        // imgNum 받아옴
                        Map<String, Object> temp = document.getData();
                        using = (String) temp.get("using");
                        seatNum = (String) temp.get("seat");
                        time = (String) temp.get("time");
                    }
                }
            }
        });
        docRef = db.collection("users").document(user.getUid());
        docRef2= db.collection(using).document(seatNum);



        // 강제종료를 밑에 구현한다.

        docRef
                .update("seat", "0")
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
                .update("status", "0")
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


        //알람 종료.
        calculateTime = calendar.getTimeInMillis();
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE, my_intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

    }
}