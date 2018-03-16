package brav0.ezit_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {

    LinearLayout layout;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference1,reference2;
    Button finishbtn;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.setTitle("Chat");
        firebaseAuth=FirebaseAuth.getInstance();
        setSupportActionBar(myToolbar);
        myToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        myToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        //listView = (ListView) findViewById(R.id.list);
        layout = (LinearLayout)findViewById(R.id.layout1);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        finishbtn = (Button) findViewById(R.id.finish);
        finishbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                            if (itemSnapshot.getKey().equals("finish1")){
                                if (itemSnapshot.getValue(int.class)==0){
                                    reference2.child("finish1").setValue(1);
                                    Toast.makeText(getApplicationContext(),"Chat would end after the other user finishes it too.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    reference2.child("finish2").setValue(1);
                                    reference2.child("status").setValue(1);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
                finishbtn.setEnabled(false);
                finish();
            }
        });

        String reqid = getIntent().getExtras().getString("key");
        userid = getIntent().getExtras().getString("user");
        database = FirebaseDatabase.getInstance();
        reference1 = database.getReference("Chat").child(reqid).child("messages");
        reference2 = database.getReference("Chat").child(reqid);

        sendButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ChatMessage item;
                String messageText = messageArea.getText().toString();
                if(!messageText.equals("")){
                    item = new ChatMessage(messageText,userid);
                    reference1.push().setValue(item);
                    messageArea.setText("");
                }
            }
        });

        //showAllOldMessages();


        reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //ChatMessage msg = dataSnapshot.getValue(ChatMessage.class);
                String message = dataSnapshot.child("messageText").getValue(String.class);//msg.getMessageText();
                String userName = dataSnapshot.child("messageUser").getValue(String.class);
                String time = dataSnapshot.child("messageTime").getValue(String.class);

                if(userName.equals(firebaseAuth.getCurrentUser().getUid())){
                    addMessageBox("You:\n" + message,time, 1);
                }
                else{
                    addMessageBox("User:\n" + message,time, 2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private void showAllOldMessages() {
        reference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()){
                    if (!itemSnapshot.getKey().equals("request") || !itemSnapshot.getKey().equals("status") || !itemSnapshot.getKey().equals("user1") || !itemSnapshot.getKey().equals("user2")){
                        if (firebaseAuth.getCurrentUser().getUid().equals(userid)){
                            addMessageBox("You:\n"+"<b>"+itemSnapshot.child("messageText").getValue(String.class)+"</b>",itemSnapshot.child("messageTime").getValue(String.class),1);
                        }
                        else {
                            addMessageBox("<b>"+itemSnapshot.child("messageText").getValue(String.class)+"</b>",itemSnapshot.child("messageTime").getValue(String.class),1);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addMessageBox(String message,String time, int type){
        TextView textView = new TextView(Chat.this);
        textView.setText(message+"\n"+time);

        if(type == 1) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 10);
            textView.setLayoutParams(lp);
            textView.setBackgroundResource(R.drawable.rounded_corner1);
        }
        else{
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 0, 0, 10);
            textView.setLayoutParams(lp);
            textView.setBackgroundResource(R.drawable.rounded_corner2);
            textView.setGravity(Gravity.RIGHT);
        }

        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}