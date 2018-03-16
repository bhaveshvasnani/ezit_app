package brav0.ezit_app;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class RequestsMade extends Fragment {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<user_dis> currentItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.fragment_requests_made,null);

        firebaseAuth = FirebaseAuth.getInstance();

        this.mRecyclerView = (RecyclerView) x.findViewById(R.id.recycle_made);

        this.mLayoutManager = new LinearLayoutManager(getContext());
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");

        setFirebaseValueListener();

        return x;
    }

    public void setFirebaseValueListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentItems = new ArrayList<>();
                for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                    if (itemSnapshot.getKey().toString().equals(firebaseAuth.getCurrentUser().getUid())) {
                        for (DataSnapshot rsnapshot : itemSnapshot.getChildren()) {
                            user_dis u = new user_dis(rsnapshot.child("request").getValue(String.class),rsnapshot.child("price").getValue(String.class),rsnapshot.child("tip").getValue(String.class),rsnapshot.child("address").getValue(String.class),rsnapshot.child("cal").child("time").child("date").getValue(int.class).toString()+"-"+rsnapshot.child("cal").child("time").child("month").getValue(int.class).toString()+"-"+(rsnapshot.child("cal").child("time").child("year").getValue(int.class)+1900)+"",rsnapshot.child("cal").child("time").child("hours").getValue(int.class).toString()+":"+rsnapshot.child("cal").child("time").child("minutes").getValue(int.class).toString(),rsnapshot.child("city").getValue(String.class),rsnapshot.child("makeid").getValue(String.class),rsnapshot.child("reqid").getValue(String.class),rsnapshot.child("accid").getValue(String.class),rsnapshot.child("status").getValue(int.class));
                            currentItems.add(u);
                        }
                    }
                    mAdapter = new RecylerAdapter1(getContext(),currentItems);
                    mRecyclerView.setAdapter(mAdapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });
    }

}
