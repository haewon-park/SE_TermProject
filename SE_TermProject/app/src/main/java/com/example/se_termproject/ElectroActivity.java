package com.example.se_termproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ElectroActivity extends AppCompatActivity {
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
    Button reservation;
    String seatNumber=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center);
        db = FirebaseFirestore.getInstance();

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
        findViewById(R.id.reservationButton).setOnClickListener(onClickListener);



        db.collection("Electronic").get()
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
        Button1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[1].equals("1"))
                    status[1]="0";
                else if(status[1].equals("0"))
                    status[1]="1";

                if(status[1].equals("1")){
                    Button1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="1";
                }
                else if(status[1].equals("0")){
                    Button1.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[2].equals("1"))
                    status[2]="0";
                else if(status[2].equals("0"))
                    status[2]="1";

                if(status[2].equals("1")){
                    Button2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="2";
                }
                else if(status[2].equals("0")){
                    Button2.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[3].equals("1"))
                    status[3]="0";
                else if(status[3].equals("0"))
                    status[3]="1";

                if(status[3].equals("1")){
                    Button3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="3";
                }
                else if(status[3].equals("0")){
                    Button3.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[4].equals("1"))
                    status[4]="0";
                else if(status[4].equals("0"))
                    status[4]="1";

                if(status[4].equals("1")){
                    Button4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="4";
                }
                else if(status[4].equals("0")){
                    Button4.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button5.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[5].equals("1"))
                    status[5]="0";
                else if(status[5].equals("0"))
                    status[5]="1";

                if(status[5].equals("1")){
                    Button5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="5";
                }
                else if(status[5].equals("0")){
                    Button5.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button6.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[6].equals("1"))
                    status[6]="0";
                else if(status[6].equals("0"))
                    status[6]="1";

                if(status[6].equals("1")){
                    Button6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="6";
                }
                else if(status[6].equals("0")){
                    Button6.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button7.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[7].equals("1"))
                    status[7]="0";
                else if(status[7].equals("0"))
                    status[7]="1";

                if(status[7].equals("1")){
                    Button7.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="7";
                }
                else if(status[7].equals("0")){
                    Button7.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button8.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[8].equals("1"))
                    status[8]="0";
                else if(status[8].equals("0"))
                    status[8]="1";

                if(status[8].equals("1")){
                    Button8.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="8";
                }
                else if(status[8].equals("0")){
                    Button8.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button9.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[9].equals("1"))
                    status[9]="0";
                else if(status[9].equals("0"))
                    status[9]="1";

                if(status[9].equals("1")){
                    Button9.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="9";
                }
                else if(status[9].equals("0")){
                    Button9.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button10.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[10].equals("1"))
                    status[10]="0";
                else if(status[10].equals("0"))
                    status[10]="1";

                if(status[10].equals("1")){
                    Button10.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="10";
                }
                else if(status[10].equals("0")){
                    Button10.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button11.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[11].equals("1"))
                    status[11]="0";
                else if(status[11].equals("0"))
                    status[11]="1";

                if(status[11].equals("1")){
                    Button11.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="11";
                }
                else if(status[11].equals("0")){
                    Button11.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button12.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[12].equals("1"))
                    status[12]="0";
                else if(status[12].equals("0"))
                    status[12]="1";

                if(status[12].equals("1")){
                    Button12.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="12";
                }
                else if(status[12].equals("0")){
                    Button12.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button13.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[13].equals("1"))
                    status[13]="0";
                else if(status[13].equals("0"))
                    status[13]="1";

                if(status[13].equals("1")){
                    Button13.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="13";
                }
                else if(status[13].equals("0")){
                    Button13.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button14.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[14].equals("1"))
                    status[14]="0";
                else if(status[14].equals("0"))
                    status[14]="1";

                if(status[14].equals("1")){
                    Button14.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="14";
                }
                else if(status[14].equals("0")){
                    Button14.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button15.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[15].equals("1"))
                    status[15]="0";
                else if(status[15].equals("0"))
                    status[15]="1";

                if(status[15].equals("1")){
                    Button15.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0, 0);
                    seatNumber="15";
                }
                else if(status[15].equals("0")){
                    Button15.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button16.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[16].equals("1"))
                    status[16]="0";
                else if(status[16].equals("0"))
                    status[16]="1";

                if(status[16].equals("1")){
                    Button16.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.seat_normal_selected,0,0);
                    seatNumber="16";
                }
                else if(status[16].equals("0")){
                    Button16.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button17.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[17].equals("1"))
                    status[17]="0";
                else if(status[17].equals("0"))
                    status[17]="1";

                if(status[17].equals("1")){
                    Button17.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="17";
                }
                else if(status[17].equals("0")){
                    Button17.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button18.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[18].equals("1"))
                    status[18]="0";
                else if(status[18].equals("0"))
                    status[18]="1";
                if(status[18].equals("1")){
                    Button18.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="18";
                }
                else if(status[18].equals("0")){
                    Button18.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button19.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[19].equals("1"))
                    status[19]="0";
                else if(status[19].equals("0"))
                    status[19]="1";

                if(status[19].equals("1")){
                    Button19.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="19";
                }
                else if(status[19].equals("0")){
                    Button19.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button20.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[20].equals("1"))
                    status[20]="0";
                else if(status[20].equals("0"))
                    status[20]="1";

                if(status[20].equals("1")){
                    Button20.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="20";
                }
                else if(status[20].equals("0")){
                    Button20.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button21.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[21].equals("1"))
                    status[21]="0";
                else if(status[21].equals("0"))
                    status[21]="1";

                if(status[21].equals("1")){
                    Button21.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="21";
                }
                else if(status[21].equals("0")){
                    Button21.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button22.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[22].equals("1"))
                    status[22]="0";
                else if(status[22].equals("0"))
                    status[22]="1";

                if(status[22].equals("1")){
                    Button22.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="22";
                }
                else if(status[22].equals("0")){
                    Button22.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button23.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[23].equals("1"))
                    status[23]="0";
                else if(status[23].equals("0"))
                    status[23]="1";

                if(status[23].equals("1")){
                    Button23.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="23";
                }
                else if(status[23].equals("0")){
                    Button23.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button24.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[24].equals("1"))
                    status[24]="0";
                else if(status[24].equals("0"))
                    status[24]="1";

                if(status[24].equals("1")){
                    Button24.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="24";
                }
                else if(status[24].equals("0")){
                    Button24.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button25.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[25].equals("1"))
                    status[25]="0";
                else if(status[25].equals("0"))
                    status[25]="1";

                if(status[25].equals("1")){
                    Button25.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="25";
                }
                else if(status[25].equals("0")){
                    Button25.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button26.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[26].equals("1"))
                    status[26]="0";
                else if(status[26].equals("0"))
                    status[26]="1";

                if(status[26].equals("1")){
                    Button26.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="26";
                }
                else if(status[26].equals("0")){
                    Button26.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button27.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[27].equals("1"))
                    status[27]="0";
                else if(status[27].equals("0"))
                    status[27]="1";

                if(status[27].equals("1")){
                    Button27.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="27";
                }
                else if(status[27].equals("0")){
                    Button27.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button28.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[28].equals("1"))
                    status[28]="0";
                else if(status[28].equals("0"))
                    status[28]="1";

                if(status[28].equals("1")){
                    Button28.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="28";
                }
                else if(status[28].equals("0")){
                    Button28.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button29.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[29].equals("1"))
                    status[29]="0";
                else if(status[29].equals("0"))
                    status[29]="1";

                if(status[29].equals("1")){
                    Button29.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="29";
                }
                else if(status[29].equals("0")){
                    Button29.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });
        Button30.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(status[30].equals("1"))
                    status[30]="0";
                else if(status[30].equals("0"))
                    status[30]="1";

                if(status[30].equals("1")){
                    Button30.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal_selected,0,0);
                    seatNumber="30";
                }
                else if(status[30].equals("0")){
                    Button30.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.seat_normal,0,0);
                    seatNumber="0";
                }
            }
        });

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.reservationButton:
                    Intent intent = new Intent(getApplicationContext(), LibraryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("seatNum", seatNumber);
                    String temp = "Electronic";
                    bundle.putString("dept",temp);
                    Log.d("testtesttest",temp);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    break;

            }
        }
    };
    private void accessUserInfoDB(final String document_id) {
        db
                .collection("Electronic")
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


                            }
                            else if (status[a].equals("0") == true) {
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