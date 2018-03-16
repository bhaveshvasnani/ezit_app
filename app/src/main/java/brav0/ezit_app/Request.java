package brav0.ezit_app;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Request extends AppCompatActivity {

    private EditText req, price, tip, address,city;
    TextView showtime, showdate;
    private FirebaseAuth firebaseAuth;
    Button date, time, submit;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
        date = (Button) findViewById(R.id.button4);
        time = (Button) findViewById(R.id.button3);
        req = (EditText) findViewById(R.id.editText);
        price = (EditText) findViewById(R.id.editText2);
        tip = (EditText) findViewById(R.id.editText3);
        address = (EditText) findViewById(R.id.editText4);
        city = (EditText) findViewById(R.id.editText5);
        showdate = (TextView) findViewById(R.id.textView12);
        showtime = (TextView) findViewById(R.id.textView13);
        submit = (Button) findViewById(R.id.btn_submit);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Request.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        int flag;
                        if (year<mYear){
                            //remove
                            flag=0;
                        }
                        else if (year==mYear){
                            if (monthOfYear<mMonth){
                                //remove
                                flag=0;
                            }
                            else if (monthOfYear==mMonth){
                                if (dayOfMonth<mDay){
                                    //remove
                                    flag=0;
                                }
                                else if (dayOfMonth==mDay){
                                    flag=1;
                                    pass=1;
                                }
                                else{
                                    flag=1;
                                    pass=2;
                                }
                            }
                            else {
                                flag=1;
                                pass=2;
                            }
                        }
                        else {
                            flag = 1;
                            pass=2;
                        }
                        if (flag==0){
                            Toast.makeText(getApplicationContext(),"Inacurrate date",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            showdate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            mYear=year;
                            mMonth=monthOfYear;
                            mDay=dayOfMonth;
                            //Toast.makeText(getApplicationContext(),"Pass: "+pass,Toast.LENGTH_SHORT).show();
                        }
                    }
                }, mYear, mMonth, mDay);

                datePickerDialog.show();
                //Toast.makeText(getApplicationContext(),"Pass: "+pass,Toast.LENGTH_SHORT).show();
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Pass: "+pass,Toast.LENGTH_SHORT).show();
                if (!showdate.getText().toString().isEmpty()) {
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                    TimePickerDialog timePickerDialog = new TimePickerDialog(Request.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            int flag;
                            if (pass == 1) {
                                if (hourOfDay < mHour) {
                                    //remove
                                    flag = 0;
                                } else if (hourOfDay == mHour) {
                                    if (minute < mMinute) {
                                        //remove
                                        flag = 0;
                                    } else {
                                        flag = 1;
                                    }
                                } else {
                                    flag = 1;
                                }
                            } else if (pass == 2) {
                                flag = 1;
                            } else {
                                flag = 0;
                            }
                            if (flag == 0) {
                                Toast.makeText(getApplicationContext(), "Inaccurate Time", Toast.LENGTH_SHORT).show();
                            } else {
                                showtime.setText(hourOfDay + ":" + minute);
                                mHour=hourOfDay;
                                mMinute=minute;
                            }
                        }
                    }, mHour, mMinute, false);
                    timePickerDialog.show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Select Date First",Toast.LENGTH_SHORT).show();
                }
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String req1 = req.getText().toString().trim();
                String price1 = price.getText().toString().trim();
                String tip1 = tip.getText().toString().trim();
                String address1 = address.getText().toString().trim();
                Calendar temp =Calendar.getInstance();
                temp.set(mYear,mMonth,mDay,mHour,mMinute);
                String date1 = showdate.getText().toString().trim();
                String time1 = showtime.getText().toString().trim();
                String city1 = city.getText().toString().trim();

                if (!req1.isEmpty() && !price1.isEmpty() && !tip1.isEmpty() && !address1.isEmpty() && !date1.isEmpty() && !time1.isEmpty()  && !city1.isEmpty()) {
                    user newuser = new user(req1, price1, tip1, address1, temp, city1, firebaseAuth.getCurrentUser().getUid(), "" , "", 0);
                    FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                    String x= myRef.child("Users").child(firebaseUser.getUid()).push().getKey();
                    myRef.child("Users").child(firebaseUser.getUid()).child(x).setValue(newuser);
                    myRef.child("Users").child(firebaseUser.getUid()).child(x).child("reqid").setValue(x);
                    Toast.makeText(getApplicationContext(), "Request Made", Toast.LENGTH_SHORT).show();
                    showNotification();
                    Intent i = new Intent(Request.this, Home.class);
                    startActivity(i);
                    finish();
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                        }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Toast.makeText(Request.this, "Oops " + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
                }
                else {
                    Toast.makeText(Request.this, "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void showNotification() {
        Intent i = new Intent(this,Home.class);
        i.putExtra("open","activity");
        PendingIntent pi = PendingIntent.getActivity(this, 0, i, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setTicker("Ezit")
                .setSmallIcon(R.mipmap.app_icon)
                .setContentTitle("Request made via Ezit.")
                .setContentText("You can get all your requests in My Activity and can also chat with the other user inside the Chat.")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

}