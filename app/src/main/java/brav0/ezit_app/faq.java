package brav0.ezit_app;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by User on 20-Jul-17.
 */

public class faq extends Fragment {
    TextView q1, a1, q2, a2, q3, a3, q4, a4, q5, a5, q6, a6;
    View v;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.faq, container, false);
        q1=(TextView)v.findViewById(R.id.qus1);
        a1=(TextView)v.findViewById(R.id.ans1);
        q2=(TextView)v.findViewById(R.id.que2);
        a2=(TextView)v.findViewById(R.id.ans2);
        q3=(TextView)v.findViewById(R.id.que3);
        a3=(TextView)v.findViewById(R.id.ans3);
        q4=(TextView)v.findViewById(R.id.qus4);
        a4=(TextView)v.findViewById(R.id.ans4);
        q5=(TextView)v.findViewById(R.id.qus5);
        a5=(TextView)v.findViewById(R.id.ans5);
        q6=(TextView)v.findViewById(R.id.qus6);
        a6=(TextView)v.findViewById(R.id.ans6);

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a1.getVisibility()==View.GONE){
                    a1.setVisibility(View.VISIBLE);
                }
                else {
                    a1.setVisibility(View.GONE);
                }
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a2.getVisibility()==View.GONE){
                    a2.setVisibility(View.VISIBLE);
                }
                else {
                    a2.setVisibility(View.GONE);
                }
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a3.getVisibility()==View.GONE){
                    a3.setVisibility(View.VISIBLE);
                }
                else {
                    a3.setVisibility(View.GONE);
                }
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a4.getVisibility()==View.GONE){
                    a4.setVisibility(View.VISIBLE);
                }
                else {
                    a4.setVisibility(View.GONE);
                }
            }
        });

        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a5.getVisibility()==View.GONE){
                    a5.setVisibility(View.VISIBLE);
                }
                else {
                    a5.setVisibility(View.GONE);
                }
            }
        });

        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (a6.getVisibility()==View.GONE){
                    a6.setVisibility(View.VISIBLE);
                }
                else {
                    a6.setVisibility(View.GONE);
                }
            }
        });

        return v;
    }
}
