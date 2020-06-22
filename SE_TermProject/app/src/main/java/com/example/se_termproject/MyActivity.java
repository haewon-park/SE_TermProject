package com.example.se_termproject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.Map;


public class MyActivity extends AppCompatActivity {
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    int max = 1;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference docRefUserInfo;
    public static int mCount;
    public static int gOut;
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


    TextView room ;
    TextView seat;
    TextView seatText ;
    LinearLayout layout;
    TextView timeText;

    Button plusButton;
    Button returnButton;
    Button GoOutButton;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        room = (TextView) findViewById(R.id.RoomText);
        seat = (TextView) findViewById(R.id.NumText);
        seatText = (TextView) findViewById(R.id.SeatText);
        layout = (LinearLayout) findViewById(R.id.layout);
        timeText = (TextView) findViewById(R.id.time);
        final TextView number = (TextView) findViewById(R.id.plusNum);
        plusButton = (Button) findViewById(R.id.button1); //연장
        returnButton = (Button) findViewById(R.id.button2); //반납
        GoOutButton = (Button) findViewById(R.id.button3); //외출
        // Initialize Firebase Auth
        docRefUserInfo = db.collection("users").document(user.getUid());
        docRef = db.collection("users").document(user.getUid());
        time = "0";
        // 유저 정보접근
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

                        if (seatNum.equals("0")) {
                            Toast.makeText(MyActivity.this, "사용 중인 좌석이 없습니다.", Toast.LENGTH_SHORT).show();

                        } else {
                            //좌석 알려주는 곳
                            //using 도서관
                            // seatNum 좌석 번호
                            room.setText(using);
                            seat.setText(seatNum);
                            timeText.setText(time);
                            docRef2= db.collection(using).document(seatNum);
                            //docRef2가 status 정보는 못 가져오는 것 같음

                        }

                    } else {
                        Log.d("userinfo", "get failed with ", task.getException());
                    }
                } else {
                    Log.d("userinfo", "get failed with ", task.getException());
                }
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCount < 1) {
                    final CharSequence[] items = new CharSequence[]{"30분", "1시간"};
                    final double[] bonus_times = new double[]{0.5, 1};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MyActivity.this);
                    dialog.setTitle("연장 시간 선택하여 주십시오");
                    dialog.setItems(items, new DialogInterface.OnClickListener() {
                        // 리스트 선택 시 이벤트
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MyActivity.this, items[which], Toast.LENGTH_SHORT).show();

                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MyActivity.this);
                            //v7 사용
                            //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                            builder.setMessage(items[which] + " 연장이 완료 되었습니다.")
                                    .setNegativeButton("확인", null)
                                    .create()
                                    .show();

                            //연장한 시간만큼 알람예약시간에 더 해주기
                            if(which == 0 ){
                                double temp = Double.parseDouble(time)+0.5;
                                Log.d("testhost",time);
                                Log.d("testhost",temp+"");

                                docRef
                                        .update("time", String.valueOf(temp))
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
                                //알람 예약 시간 30분 추가

                                SharedPreferences pref =getSharedPreferences("pref", MODE_PRIVATE);
                                String result = "";
                                if(pref != null) {
                                    result = pref.getString("set_min", "0"); //키값, 디폴트값
                                }
                                else
                                    Log.e("ERROR_setmin", "set_min 값이 안 불러와집니다.");
                                int asdf = Integer.parseInt(result);

                                asdf+=30;
                                Log.e("result", "더해진 값은?" + result);
                                result = Integer.toString(asdf);

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("set_min", result); //키값, 저장값
                            }

                            else if(which ==1){
                                Double temp = Double.parseDouble(time)+1;
                                Log.d("testhost",time);
                                Log.d("testhost",temp+"");

                                docRef
                                        .update("time", String.valueOf(temp))
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

                                //알람 예약 시간 1시간 추가
                                SharedPreferences pref =getSharedPreferences("pref", MODE_PRIVATE);
                                String result = pref.getString("set_hour", "0"); //키값, 디폴트값

                                int asdf = Integer.parseInt(result);

                                asdf+=1;

                                result = Integer.toString(asdf);

                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("set_hour", result); //키값, 저장값
                            }
                        }
                    });

                    dialog.show();
                    mCount++;
                    int rr = max - mCount;
                    number.setText(Integer.valueOf(mCount) + " (" + rr + "회 남음)");
                } else {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MyActivity.this);
                    //v7 사용
                    //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                    builder.setMessage("연장횟수를 초과했습니다.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
                if (mCount == 1) {
                    number.setText("1 (0회 남음)");
                }
            }
        });


        returnButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MyActivity.this);
                //v7 사용
                //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                builder.setMessage("반납하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
                        docRef
                                .update("time", "0")
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

                        /*db 내역 다시 불러오기*/

                        time="";
                        seatNum="";
                        time="";

                        room.setText(using);
                        seat.setText(seatNum);
                        timeText.setText(time);

                        mCount = 0;
                        gOut = 0;

                        //calculateTime = calendar.getTimeInMillis();
                        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), REQUEST_CODE, my_intent,
                                PendingIntent.FLAG_CANCEL_CURRENT);


                        finish();

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

        GoOutButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (gOut < 1) {
                    final CharSequence[] items = new CharSequence[]{"예", "아니오"};
                    final double[] bonus_times = new double[]{0.5, 1};
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MyActivity.this);
                    dialog.setTitle("외출하시겠습니까?");
                    dialog.setItems(items, new DialogInterface.OnClickListener() {
                        // 리스트 선택 시 이벤트
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(MyActivity.this, items[which], Toast.LENGTH_SHORT).show();

                            androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MyActivity.this);
                            //v7 사용
                            //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                            builder.setMessage("30분 동안 외출합니다.")
                                    .setPositiveButton("확인", null)
                                    .create()
                                    .show();

                            docRef2
                                    .update("status", "2")
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

                            //연장한 시간만큼 알람예약시간에 더 해주기

                            double temp = Double.parseDouble(time) + 0.5;
                            Log.d("testhost", time);
                            Log.d("testhost", temp + "");

                            docRef
                                    .update("time", String.valueOf(temp))
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
                            //알람 예약 시간 30분 추가

                            SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
                            String result = "";
                            if (pref != null) {
                                result = pref.getString("set_min", "0"); //키값, 디폴트값
                            } else
                                Log.e("ERROR_setmin", "set_min 값이 안 불러와집니다.");
                            int asdf = Integer.parseInt(result);

                            asdf += 30;
                            Log.e("result", "더해진 값은?" + result);
                            result = Integer.toString(asdf);

                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("set_min", result); //키값, 저장값
                        }
                    });
                    dialog.show();
                    gOut++;
                } else {
                    androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(MyActivity.this);
                    //v7 사용
                    //android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(MyActivity.this);
                    builder.setMessage("이전에 외출을 하셨습니다, 외출은 한 번만 가능합니다.")
                            .setNegativeButton("확인", null)
                            .create()
                            .show();
                }
            }
        });

        if (seatNum == "0") {
            seatText.setVisibility(View.VISIBLE);
            layout.setVisibility(View.GONE);
            room.setText(null);
            seat.setText(null);
            timeText.setText(null);
            number.setText(null);
        }

        else {
            seatText.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}