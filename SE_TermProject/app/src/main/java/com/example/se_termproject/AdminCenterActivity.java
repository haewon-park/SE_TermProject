package com.example.se_termproject;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

// Admin Center reservation
public class AdminCenterActivity extends AppCompatActivity {
    FirebaseFirestore db;
    String[] status = new String[31];
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;
    Button Button8;
    Button Button9;
    Button Button10;
    Button Button11;
    Button Button12;
    Button Button13;
    Button Button14;
    Button Button15;
    Button Button16;
    Button Button17;
    Button Button18;
    Button Button19;
    Button Button20;
    Button Button21;
    Button Button22;
    Button Button23;
    Button Button24;
    Button Button25;
    Button Button26;
    Button Button27;
    Button Button28;
    Button Button29;
    Button Button30;
    String seatNumber = null;
    String location;

    //firebase
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();


    DocumentReference docRefUserInfo;
    String time;
    String seatNum;
    String using;
    String userId;
    String statusTemp;
    DocumentReference docRef;
    DocumentReference docSeat;

    //Alarm
    AlarmManager alarm_manager;
    private Calendar calendar;
    PendingIntent pendingIntent;
    private Intent my_intent;
    private static final int REQUEST_CODE = 3333;
    public long calculateTime = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_center);
        db = FirebaseFirestore.getInstance();
        my_intent = new Intent(getApplicationContext(), Alarm_Receiver.class);
        location="Center";
        Button1 = findViewById(R.id.Button1);
        Button2 = findViewById(R.id.Button2);
        Button3 = findViewById(R.id.Button3);
        Button4 = findViewById(R.id.Button4);
        Button5 = findViewById(R.id.Button5);
        Button6 = findViewById(R.id.Button6);
        Button7 = findViewById(R.id.Button7);
        Button8 = findViewById(R.id.Button8);
        Button9 = findViewById(R.id.Button9);
        Button10 = findViewById(R.id.Button10);
        Button11 = findViewById(R.id.Button11);
        Button12 = findViewById(R.id.Button12);
        Button13 = findViewById(R.id.Button13);
        Button14 = findViewById(R.id.Button14);
        Button15 = findViewById(R.id.Button15);
        Button16 = findViewById(R.id.Button16);
        Button17 = findViewById(R.id.Button17);
        Button18 = findViewById(R.id.Button18);
        Button19 = findViewById(R.id.Button19);
        Button20 = findViewById(R.id.Button20);
        Button21 = findViewById(R.id.Button21);
        Button22 = findViewById(R.id.Button22);
        Button23 = findViewById(R.id.Button23);
        Button24 = findViewById(R.id.Button24);
        Button25 = findViewById(R.id.Button25);
        Button26 = findViewById(R.id.Button26);
        Button27 = findViewById(R.id.Button27);
        Button28 = findViewById(R.id.Button28);
        Button29 = findViewById(R.id.Button29);
        Button30 = findViewById(R.id.Button30);

        db.collection(location).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                accessUserInfoDB(document.getId());

                            }
                        } else {

                        }
                    }
                });

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("1");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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


        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("2");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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


        Button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("3");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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



        Button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("4");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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


        Button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("5");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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

        Button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("6");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("7");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("8");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("9");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("10");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("11");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("12");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("13");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("14");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("15");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("16");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("17");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("18");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("19");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("20");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("21");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        Button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("23");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("24");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("25");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("26");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("27");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        Button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("28");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("29");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
        });Button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(AdminCenterActivity.this);
                builder.setMessage("강제 퇴실하시겠습니까?");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        docSeat = db.collection(location).document("30");
                        docSeat.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        // imgNum 받아옴
                                        Map<String, Object> temp = document.getData();
                                        seatNum = (String) temp.get("seatNum");
                                        statusTemp = (String) temp.get("status");
                                        userId = (String) temp.get("user");

                                        docRef = db.collection("users").document(userId);

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
                                        docRef
                                                .update("using", "0")
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

                                        docSeat
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
                                        docSeat
                                                .update("user", "0")
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
                                    }
                                }
                            }
                        });


                        //DB 내역 지우기 유저한테서 이용중인 좌석 번호 지우고
                        //이용했던 좌석 상태도 0으로 전환.

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
    }

    private void accessUserInfoDB(final String document_id) {
        db
                .collection(location)
                .document(document_id)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot result = task.getResult();
                            Map<String, Object> data = result.getData();
                            String temp = (String) data.get("status");
                            int a = Integer.parseInt(document_id);
                            Log.d("testtest", temp);
                            status[a] = temp;


                            if (status[a].equals("1") == true) {
                                switch (a) {
                                    case 1:
                                        Button1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 2:
                                        Button2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 3:
                                        Button3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 4:
                                        Button4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 5:
                                        Button5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 6:
                                        Button6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 7:
                                        Button7.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 8:
                                        Button8.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 9:
                                        Button9.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 10:
                                        Button10.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 11:
                                        Button11.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 12:
                                        Button12.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 13:
                                        Button13.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 14:
                                        Button14.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 15:
                                        Button15.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 16:
                                        Button16.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 17:
                                        Button17.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 18:
                                        Button18.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 19:
                                        Button19.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 20:
                                        Button20.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 21:
                                        Button21.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 22:
                                        Button22.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 23:
                                        Button23.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 24:
                                        Button24.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 25:
                                        Button25.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 26:
                                        Button26.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 27:
                                        Button27.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 28:
                                        Button28.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 29:
                                        Button29.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                    case 30:
                                        Button30.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected, 0, 0);
                                        break;
                                }


                            } else if (status[a].equals("0") == true) {
                                switch (a) {
                                    case 1:
                                        Button1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 2:
                                        Button2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 3:
                                        Button3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 4:
                                        Button4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 5:
                                        Button5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 6:
                                        Button6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 7:
                                        Button7.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 8:
                                        Button8.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 9:
                                        Button9.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 10:
                                        Button10.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 11:
                                        Button11.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 12:
                                        Button12.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 13:
                                        Button13.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 14:
                                        Button14.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 15:
                                        Button15.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 16:
                                        Button16.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 17:
                                        Button17.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 18:
                                        Button18.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 19:
                                        Button19.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 20:
                                        Button20.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 21:
                                        Button21.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 22:
                                        Button22.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 23:
                                        Button23.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 24:
                                        Button24.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 25:
                                        Button25.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 26:
                                        Button26.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 27:
                                        Button27.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 28:
                                        Button28.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 29:
                                        Button29.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                    case 30:
                                        Button30.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal, 0, 0);
                                        break;
                                }


                            }

                        }
                    }
                });
    }
}