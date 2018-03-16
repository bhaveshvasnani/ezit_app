package brav0.ezit_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by User on 26-Jun-17.
 */

public class RecylerAdapter1 extends RecyclerView.Adapter<RecylerAdapter1.ViewHolder> {

    List<user_dis> mDataset;
    public View view;
    Context c;
    AlertDialog.Builder builder;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView req, pri, tip, add, cit, dat, tim;
        CardView cd;
        ItemClickListener itemClickListener;

        public ViewHolder(View v) {
            super(v);
            req = (TextView) itemView.findViewById(R.id.textView22);
            pri = (TextView) itemView.findViewById(R.id.textView24);
            tip = (TextView) itemView.findViewById(R.id.textView26);
            add = (TextView) itemView.findViewById(R.id.textView28);
            cit = (TextView) itemView.findViewById(R.id.textView31);
            dat = (TextView) itemView.findViewById(R.id.textView33);
            tim = (TextView) itemView.findViewById(R.id.textView35);
            cd = (CardView) itemView.findViewById(R.id.card_view);
            v.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener=itemClickListener;
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onItemClick(this.getLayoutPosition());
        }
    }

    public RecylerAdapter1(Context c,List<user_dis> items) {
        mDataset = items;
        this.c=c;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        firebaseAuth = FirebaseAuth.getInstance();

        RecylerAdapter1.ViewHolder vh = new RecylerAdapter1.ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.req.setText(mDataset.get(position).getRequest());
        holder.pri.setText(mDataset.get(position).getPrice());
        holder.tip.setText(mDataset.get(position).getTip());
        holder.add.setText(mDataset.get(position).getAddress());
        holder.cit.setText(mDataset.get(position).getCity());
        holder.dat.setText(mDataset.get(position).getDate());
        holder.tim.setText(mDataset.get(position).getTime());
        //int[] androidColors = c.getResources().getIntArray(R.array.androidcolors);
        //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        //holder.cd.setBackgroundColor(randomAndroidColor);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                //Toast.makeText(c,mDataset.get(pos).toString(),Toast.LENGTH_SHORT).show();
                builder = new AlertDialog.Builder(c);
                builder.setCancelable(true);
                builder.setTitle("Confirm");
                builder.setMessage("Do you want to delete request?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    myRef.child(firebaseAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                            for (DataSnapshot itemSnapshot: dataSnapshot.getChildren()) {
                                                if (itemSnapshot.getKey().equals(mDataset.get(position).getReqid())){
                                                    if (itemSnapshot.child("accid").getValue(String.class).equals("") && itemSnapshot.child("status").getValue(int.class).equals(0)){
                                                        myRef.child(firebaseAuth.getCurrentUser().getUid()).child(itemSnapshot.getKey()).child("status").setValue(1);
                                                    }
                                                    else {
                                                        Toast.makeText(c,"Can't delete as it is either expired, deleted or accepted already.",Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }

                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                // Toast.makeText(c,"Yo",Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
