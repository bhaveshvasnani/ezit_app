package brav0.ezit_app;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 07-Jun-17.
 */

public class FourthFragment extends Fragment {

    /*private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ListView lv1;
    ArrayAdapter<String> adapter;
    List<String> currentItems;

    public FourthFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.fourth,null);

        firebaseAuth = FirebaseAuth.getInstance();

        /*this.mRecyclerView = (RecyclerView) x.findViewById(R.id.recycle_chat);

        this.mLayoutManager = new LinearLayoutManager(getContext());
        this.mRecyclerView.setLayoutManager(mLayoutManager);
        lv1=(ListView)x.findViewById(R.id.lv);

        lv1.setOnClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext(), Chat.class);
                startActivity(i);
            }
        });

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Chat");
        //Toast.makeText(getContext(),"hi",Toast.LENGTH_SHORT).show();

        setFirebaseValueListener();

        return x;
    }

    public void setFirebaseValueListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentItems = new ArrayList<>();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.child("status").getValue(int.class)==0 && (itemSnapshot.child("user1").getValue(String.class).equals(firebaseAuth.getCurrentUser().getUid()) || itemSnapshot.child("user2").getValue(String.class).equals(firebaseAuth.getCurrentUser().getUid()))) {
                        currentItems.add(itemSnapshot.child("request").getValue(String.class));
                    }

                    mAdapter = new RecyclerAdapterChat(getContext(),currentItems);
                    mRecyclerView.setAdapter(mAdapter);
                    //String[] x = {"A","B","C"};
                    adapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1, currentItems);
                    lv1.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }



}*/

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<chat_user> currentItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.fourth,null);

        firebaseAuth = FirebaseAuth.getInstance();

        this.mRecyclerView = (RecyclerView) x.findViewById(R.id.recycle_Chat);

        this.mLayoutManager = new LinearLayoutManager(getContext());
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Chat");
        //myRef2 = database.getReference("Chat").child(firebaseAuth.getCurrentUser().getUid())

        setFirebaseValueListener();

        return x;
    }

    public void setFirebaseValueListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentItems = new ArrayList<>();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.child("status").getValue(int.class) == 0){
                        if (itemSnapshot.child("user1").getValue(String.class).equals(firebaseAuth.getCurrentUser().getUid())) {
                            chat_user item = new chat_user(itemSnapshot.child("request").getValue(String.class),itemSnapshot.child("user1").getValue(String.class),itemSnapshot.child("user2").getValue(String.class),itemSnapshot.getKey(),itemSnapshot.child("status").getValue(int.class));
                            currentItems.add(item);
                        }
                        else  if (itemSnapshot.child("user2").getValue(String.class).equals(firebaseAuth.getCurrentUser().getUid())){
                            chat_user item = new chat_user(itemSnapshot.child("request").getValue(String.class),itemSnapshot.child("user2").getValue(String.class),itemSnapshot.child("user1").getValue(String.class),itemSnapshot.getKey(),itemSnapshot.child("status").getValue(int.class));
                            currentItems.add(item);
                        }
                    }
                    mAdapter = new RecyclerAdapter2(getContext(),currentItems);
                    mRecyclerView.setAdapter(mAdapter);
                }

                /*for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.getKey().toString().equals(firebaseAuth.getCurrentUser().getUid())) {
                        for (DataSnapshot reqSnapshot : itemSnapshot.getChildren()) {
                            if (reqSnapshot.child().child("status").getValue(int.class)==0){
                                user item = new user(reqSnapshot.child("request").getValue(String.class), reqSnapshot.child("price").getValue(String.class), reqSnapshot.child("tip").getValue(String.class), reqSnapshot.child("address").getValue(String.class), reqSnapshot.child("date").getValue(String.class), reqSnapshot.child("time").getValue(String.class), reqSnapshot.child("city").getValue(String.class), reqSnapshot.child("makeid").getValue(String.class), reqSnapshot.child("reqid").getValue(String.class), reqSnapshot.child("accid").getValue(String.class),reqSnapshot.child("status").getValue(int.class));
                                currentItems.add(item);
                            }
                        }
                    }
                    mAdapter = new RecylerAdapter1(getContext(),currentItems);
                    mRecyclerView.setAdapter(mAdapter);
                }*/

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

    public FourthFragment(){

    }

}
