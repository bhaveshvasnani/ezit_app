package brav0.ezit_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;


/**
 * Created by User on 30-Jun-17.
 */

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerAdapter2.ViewHolder>{

    List<chat_user> mDataset;
    public View view;
    Context c;
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;
    DatabaseReference myRef;

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView req, user;
        CardView cd;
        ItemClickListener itemClickListener;

        public ViewHolder(View v) {
            super(v);
            req = (TextView) itemView.findViewById(R.id.textView11);
            cd = (CardView) itemView.findViewById(R.id.card_view1);
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

    public RecyclerAdapter2(Context c,List<chat_user> items) {
        mDataset = items;
        this.c=c;
    }

    @Override
    public RecyclerAdapter2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_chat, parent, false);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Chat");
        firebaseAuth = FirebaseAuth.getInstance();

        RecyclerAdapter2.ViewHolder vh = new RecyclerAdapter2.ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter2.ViewHolder holder, final int position) {
        holder.req.setText(mDataset.get(position).getReq());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemClick(int pos) {
                Intent i = new Intent(c,Chat.class);
                String key = mDataset.get(position).getReqid();
                String usr = mDataset.get(position).getUser1();
                i.putExtra("key",key);
                i.putExtra("user",usr);
                c.startActivity(i);
            }
        });
        //int[] androidColors = c.getResources().getIntArray(R.array.androidcolors);
        //int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        //holder.cd.setBackgroundColor(randomAndroidColor);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
