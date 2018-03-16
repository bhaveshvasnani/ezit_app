package brav0.ezit_app;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.text1;
import static brav0.ezit_app.FilterHelper.adapter;

/**
 * Created by User on 07-Jun-17.
 */

public class SecondFragment extends Fragment {

    View myView;

    public SecondFragment(){

    }

    /*
    RecyclerView mRecyclerView;
    private GridLayoutManager layoutManager;
    //RecyclerView.LayoutManager mLayoutManager;
    RVAdapter adapter;

    public SecondFragment(){
        List<About> persons= new ArrayList<>();
        persons.add(new About("A", "23 years old", R.drawable.about_us_icon));
        persons.add(new About("B", "25 years old", R.drawable.app_icon));
        persons.add(new About("C", "35 years old", R.drawable.chat_icon));
        persons.add(new About("D", "35 years old", R.drawable.about_us_icon));
        adapter = new RVAdapter(getContext(),persons);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second,container,false);
        mRecyclerView = (RecyclerView)myView.findViewById(R.id.rv);
        //this.mLayoutManager = new LinearLayoutManager(getContext());
        //this.mRecyclerView.setLayoutManager(mLayoutManager);
        layoutManager = new GridLayoutManager(getContext(),2);
        this.mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(adapter);
        return myView;
    }*/

    public ImageView b,r,a,v,o,fb,lin,g;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.second,container,false);
        b=(ImageView) myView.findViewById(R.id.imageView17);
        r=(ImageView) myView.findViewById(R.id.imageView18);
        a=(ImageView) myView.findViewById(R.id.imageView19);
        v=(ImageView) myView.findViewById(R.id.imageView20);
        o=(ImageView) myView.findViewById(R.id.imageView21);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup);
                TextView name = (TextView) dialog.findViewById(R.id.text1);
                TextView rank = (TextView)dialog.findViewById(R.id.text2);
                TextView info = (TextView)dialog.findViewById(R.id.text3);
                ImageView img = (ImageView) dialog.findViewById(R.id.image);
                fb=(ImageView) dialog.findViewById(R.id.imageView6);
                lin=(ImageView) dialog.findViewById(R.id.imageView11);
                g=(ImageView) dialog.findViewById(R.id.imageView9);
                name.setText("Bhavesh Vasnani");
                name.setTypeface(null, Typeface.BOLD);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                rank.setText("Co-Founder");
                rank.setTypeface(null, Typeface.BOLD);
                rank.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                info.setText("A");
                img.setImageResource(R.drawable.about_us_icon);
                fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent FacebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bhaveshvasnani"));
                        startActivity(FacebookIntent);
                    }
                });
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in//bhavesh-vasnani-29a359137"));
                        startActivity(LinkedInIntent);
                    }
                });
                g.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+BhaveshVasnani"));
                        startActivity(LinkedInIntent);
                    }
                });
                TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setBackgroundColor(Color.WHITE);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup);
                TextView name = (TextView) dialog.findViewById(R.id.text1);
                TextView rank = (TextView)dialog.findViewById(R.id.text2);
                TextView info = (TextView)dialog.findViewById(R.id.text3);
                ImageView img = (ImageView) dialog.findViewById(R.id.image);
                fb=(ImageView) dialog.findViewById(R.id.imageView6);
                lin=(ImageView) dialog.findViewById(R.id.imageView11);
                g=(ImageView) dialog.findViewById(R.id.imageView9);
                name.setText("Rakshit Hazrati");
                name.setTypeface(null, Typeface.BOLD);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                rank.setText("Co-Founder");
                rank.setTypeface(null, Typeface.BOLD);
                rank.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                info.setText("B");
                img.setImageResource(R.drawable.about_us_icon);
                fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent FacebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/rakshit.hazrati?fref=ts"));
                        startActivity(FacebookIntent);
                    }
                });
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/rakshit-hazrati-72062b147"));
                        startActivity(LinkedInIntent);
                    }
                });
                g.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/115500703412235577084"));
                        startActivity(LinkedInIntent);
                    }
                });
                img.setImageResource(R.drawable.about_us_icon);
                TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setBackgroundColor(Color.WHITE);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup);
                TextView name = (TextView) dialog.findViewById(R.id.text1);
                TextView rank = (TextView)dialog.findViewById(R.id.text2);
                TextView info = (TextView)dialog.findViewById(R.id.text3);
                ImageView img = (ImageView) dialog.findViewById(R.id.image);
                fb=(ImageView) dialog.findViewById(R.id.imageView6);
                lin=(ImageView) dialog.findViewById(R.id.imageView11);
                g=(ImageView) dialog.findViewById(R.id.imageView9);
                name.setText("Aman Goyal");
                name.setTypeface(null, Typeface.BOLD);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                rank.setText("Co-Founder");
                rank.setTypeface(null, Typeface.BOLD);
                rank.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                img.setImageResource(R.drawable.about_us_icon);
                fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent FacebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/aman.goyalwd"));
                        startActivity(FacebookIntent);
                    }
                });
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/aman-goyal-32541a115"));
                        startActivity(LinkedInIntent);
                    }
                });
                g.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/117996036698069817181"));
                        startActivity(LinkedInIntent);
                    }
                });
                img.setImageResource(R.drawable.about_us_icon);
                info.setText("C");
                img.setImageResource(R.drawable.about_us_icon);
                TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setBackgroundColor(Color.WHITE);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup);
                TextView name = (TextView) dialog.findViewById(R.id.text1);
                TextView rank = (TextView)dialog.findViewById(R.id.text2);
                TextView info = (TextView)dialog.findViewById(R.id.text3);
                fb=(ImageView) dialog.findViewById(R.id.imageView6);
                lin=(ImageView) dialog.findViewById(R.id.imageView11);
                g=(ImageView) dialog.findViewById(R.id.imageView9);
                ImageView img = (ImageView) dialog.findViewById(R.id.image);
                name.setText("Varun D Suvarna");
                name.setTypeface(null, Typeface.BOLD);
                name.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                rank.setText("Co-Founder");
                rank.setTypeface(null, Typeface.BOLD);
                rank.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);
                info.setText("B");
                img.setImageResource(R.drawable.about_us_icon);
                fb.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent FacebookIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/vibrantvds?fref=ts"));
                        startActivity(FacebookIntent);
                    }
                });
                lin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/vibrantvds"));
                        startActivity(LinkedInIntent);
                    }
                });
                g.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent LinkedInIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/+VarunDamodarSuvarna"));
                        startActivity(LinkedInIntent);
                    }
                });
                img.setImageResource(R.drawable.about_us_icon);
                TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setBackgroundColor(Color.WHITE);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                info.setText("D");
                img.setImageResource(R.drawable.about_us_icon);
                dialog.show();
            }
        });

        o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.popup);
                TextView name = (TextView) dialog.findViewById(R.id.text1);
                TextView rank = (TextView)dialog.findViewById(R.id.text2);
                TextView info = (TextView)dialog.findViewById(R.id.text3);
                ImageView img = (ImageView) dialog.findViewById(R.id.image);
                img.setVisibility(View.INVISIBLE);
                fb=(ImageView) dialog.findViewById(R.id.imageView6);
                lin=(ImageView) dialog.findViewById(R.id.imageView11);
                g=(ImageView) dialog.findViewById(R.id.imageView9);
                fb.setVisibility(View.INVISIBLE);
                lin.setVisibility(View.INVISIBLE);
                g.setVisibility(View.INVISIBLE);
                name.setText("");
                rank.setText("");
                info.setText("As a tribute to the technological industry");
                info.setTypeface(null, Typeface.BOLD);
                info.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);
                TextView dialogButton = (TextView) dialog.findViewById(R.id.dialogButtonOK);
                dialogButton.setBackgroundColor(Color.WHITE);
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        return myView;
    }


}
