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

public class RecyclerAcc extends RecyclerView.Adapter<RecyclerAcc.ViewHolder> {

    List<user_dis> mDataset;
    public View view;
    Context c;
    AlertDialog.Builder builder;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;

    public static class ViewHolder extends RecyclerView.ViewHolder{
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
            cd = (CardView) itemView.findViewById(R.id.card_view);
            tim = (TextView) itemView.findViewById(R.id.textView35);
        }
    }

    public RecyclerAcc(Context c,List<user_dis> items) {
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

        RecyclerAcc.ViewHolder vh = new RecyclerAcc.ViewHolder(v);
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
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
