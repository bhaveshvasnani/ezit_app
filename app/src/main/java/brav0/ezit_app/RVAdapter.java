package brav0.ezit_app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.Random;

/**
 * Created by User on 18-Jul-17.
 */
/*
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.PersonViewHolder> {

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView personName;
        TextView personinfo;
        ImageView personPhoto;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personinfo = (TextView) itemView.findViewById(R.id.person_info);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
    }


    List<About> persons;

    RVAdapter(List<About> persons) {
        this.persons = persons;
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_about, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.personName.setText(persons.get(i).name);
        personViewHolder.personinfo.setText(persons.get(i).info);
        personViewHolder.personPhoto.setImageResource(persons.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
*/
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder>{

    List<About> mDataset;
    public View view;
    Context c;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView name,info;
        public ImageView img;
        CardView cd;

        public ViewHolder(View v) {
            super(v);
            name = (TextView) itemView.findViewById(R.id.person_name);
            cd = (CardView) itemView.findViewById(R.id.card_view);
            info = (TextView) itemView.findViewById(R.id.person_info);
            img = (ImageView) itemView.findViewById(R.id.person_photo);
        }

    }









    public RVAdapter(Context c,List<About> items) {
        mDataset = items;
        this.c=c;
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_about, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int i) {
        holder.name.setText(mDataset.get(i).name);
        holder.info.setText(mDataset.get(i).info);
        int[] androidColors = c.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        holder.cd.setBackgroundColor(randomAndroidColor);
        holder.img.setImageResource(mDataset.get(i).photoId);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
