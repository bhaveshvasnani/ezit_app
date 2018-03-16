package brav0.ezit_app;

import android.app.Activity;
import android.app.Application;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.crash.FirebaseCrash;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import com.google.firebase.auth.FirebaseAuth;

import static android.R.id.list;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    RecyclerAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<user_dis> currentItems;
    boolean doubleBackToExitPressedOnce = false;
    boolean exit = false;
    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private ProgressDialog Dialog;
    final Calendar c = Calendar.getInstance();
    private int mYear, mMonth, mDay, mHour, mMinute;
    String y1,m1,d1,h1,mm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        Dialog = new ProgressDialog(this);
        Dialog.setMessage("Getting Requests...");
        Dialog.show();
        //Date cDate = new Date();
        //Toast.makeText(getApplicationContext(),new SimpleDateFormat("EEE, MMM dd, yyyy h:mm a").format(cDate),Toast.LENGTH_LONG).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setTitle("Home");
        firebaseAuth = FirebaseAuth.getInstance();

        this.mRecyclerView = (RecyclerView) findViewById(R.id.recycle);

        this.mLayoutManager = new LinearLayoutManager(this);
        this.mRecyclerView.setLayoutManager(mLayoutManager);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Users");
        FirebaseCrash.log("Got reference");

        setFirebaseValueListener();
        Dialog.dismiss();

        SearchView search = (SearchView) findViewById( R.id.sv);
        TextView textView = (TextView) search.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        textView.setTextColor(Color.WHITE);
        textView.setHintTextColor(Color.WHITE);
        search.setQueryHint("Filter using City");
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(final String query) {
                //adapter.getFilter().filter(query);
                //Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        currentItems = new ArrayList<>();
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);
                        //Toast.makeText(getApplicationContext(),"gfdsadf",Toast.LENGTH_SHORT).show();
                        for (DataSnapshot itemSnapshot : dataSnapshot.getChildren()) {
                            if (itemSnapshot.getKey().toString().equals(firebaseAuth.getCurrentUser().getUid())) {
                            } else {
                                for (DataSnapshot reqSnapshot : itemSnapshot.getChildren()) {
                                    if (reqSnapshot.child("status").getValue(int.class) == 0 && reqSnapshot.child("accid").getValue(String.class).isEmpty()) {
                                        Toast.makeText(getApplicationContext(),reqSnapshot.child("request").getValue(String.class),Toast.LENGTH_SHORT).show();
                                        Calendar c = Calendar.getInstance();
                                        if (c.compareTo(reqSnapshot.child("cal").getValue(Calendar.class))<=0){
                                            //user item = new user(reqSnapshot.child("request").getValue(String.class), reqSnapshot.child("price").getValue(String.class), reqSnapshot.child("tip").getValue(String.class), reqSnapshot.child("address").getValue(String.class), reqSnapshot.child("cal").getValue(Calendar.class), reqSnapshot.child("city").getValue(String.class), reqSnapshot.child("makeid").getValue(String.class), reqSnapshot.child("reqid").getValue(String.class), reqSnapshot.child("accid").getValue(String.class), reqSnapshot.child("status").getValue(int.class));
                                            //currentItems.add(item);
                                        }
                                    }
                                }
                            }
                            mAdapter = new RecyclerAdapter(Home.this, currentItems);
                            mRecyclerView.setAdapter(mAdapter);
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }



        });

        search.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                setFirebaseValueListener();
                return false;
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this,Request.class);
                startActivity(i);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        TextView txtProfileName = (TextView) navigationView.getHeaderView(0).findViewById(R.id.user_email);
        txtProfileName.setText(firebaseAuth.getCurrentUser().getEmail());

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
            {
                finishAffinity();
            }
            else {
                Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT).show();
                List<android.support.v4.app.Fragment> fragments = getSupportFragmentManager().getFragments();
                if (fragments != null) {
                    for (android.support.v4.app.Fragment fragment : fragments) {
                        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        //mRecyclerView.requestDisallowInterceptTouchEvent(false);
                        getSupportActionBar().setTitle("Home");

                    }
                }
            }
            mBackPressed = System.currentTimeMillis();
        }

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Toast.makeText(getApplicationContext(), "No User Settings Currently Available",Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        //Context context=getApplicationContext();
        //FragmentManager fragmentManager = /*((AppCompatActivity)context).*/getFragmentManager();

        if (id == R.id.nav_first) {
            getSupportActionBar().setTitle("My Activity");
            Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.hy, new FirstFragment()).addToBackStack(null).commit();
            //mRecyclerView.requestDisallowInterceptTouchEvent(true);
        } else if (id == R.id.nav_second) {
            getSupportActionBar().setTitle("About Us");
            Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.hy, new SecondFragment()).addToBackStack(null).commit();
            Toast.makeText(getApplicationContext(),"Click on every letter for detail.",Toast.LENGTH_SHORT).show();
            //mRecyclerView.requestDisallowInterceptTouchEvent(true);
        } else if (id == R.id.nav_third) {
            getSupportActionBar().setTitle("Contact Us");
            Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.hy, new ThirdFragment()).addToBackStack(null).commit();
            //mRecyclerView.requestDisallowInterceptTouchEvent(true);
        } else if (id == R.id.nav_fourth) {
            getSupportActionBar().setTitle("Chat");
            Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.hy, new FourthFragment()).addToBackStack(null).commit();
            //mRecyclerView.requestDisallowInterceptTouchEvent(true);
        } else if (id == R.id.nav_faq) {
            getSupportActionBar().setTitle("Help");
            Toast.makeText(getApplicationContext(),"Press back for Home",Toast.LENGTH_SHORT).show();
            getSupportFragmentManager().beginTransaction().replace(R.id.hy, new help()).addToBackStack(null).commit();
            //mRecyclerView.requestDisallowInterceptTouchEvent(true);
        } else if (id == R.id.nav_logout){
            firebaseAuth.getInstance().signOut();
            Intent i = new Intent(Home.this,login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFirebaseValueListener(){
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                currentItems = new ArrayList<>();
                int flag=0;
                int y = Calendar.getInstance().get(Calendar.YEAR);
                int mo = Calendar.getInstance().get(Calendar.MONTH);
                int d = Calendar.getInstance().get(Calendar.DATE);
                int h = Calendar.getInstance().get(Calendar.HOUR);
                int a = Calendar.getInstance().get(Calendar.AM_PM);
                if (a==1){
                    h+=12;
                }
                int m = Calendar.getInstance().get(Calendar.MINUTE);
                Toast.makeText(getApplicationContext(),Integer.toString(m),Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),Integer.toString(flag),Toast.LENGTH_SHORT).show();
                for (DataSnapshot usnapshot : dataSnapshot.getChildren()) {
                    if (!usnapshot.getKey().equals(firebaseAuth.getCurrentUser().getUid())){
                        for (DataSnapshot rsnapshot : usnapshot.getChildren()) {
                            //Toast.makeText(getApplicationContext(),rsnapshot.getKey(),Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),rsnapshot.child("cal").child("time").child("date").getValue(int.class).toString()+"-"+rsnapshot.child("cal").child("time").child("month").getValue(int.class).toString()+"-"+rsnapshot.child("cal").child("time").child("year").getValue(int.class).toString(),Toast.LENGTH_SHORT).show();
                            if (rsnapshot.child("status").getValue(int.class) == 0 && rsnapshot.child("accid").getValue(String.class).isEmpty()) {
                                //boolean i= co.after(rsnapshot.child("cal").getValue(Calendar.class));
                                if ((rsnapshot.child("cal").child("time").child("year").getValue(int.class)) >= y-1900) {
                                    Toast.makeText(getApplicationContext(),"y",Toast.LENGTH_SHORT).show();
                                    //Toast.makeText(getApplicationContext(),rsnapshot.child("cal").child("time").child("year").getValue(int.class).toString(),Toast.LENGTH_SHORT).show();
                                    if ((rsnapshot.child("cal").child("time").child("month").getValue(int.class)) >= mo) {
                                        Toast.makeText(getApplicationContext(),"mo",Toast.LENGTH_SHORT).show();
                                        //Toast.makeText(getApplicationContext(),Integer.toString(flag),Toast.LENGTH_SHORT).show();
                                        if ((rsnapshot.child("cal").child("time").child("date").getValue(int.class)) >= d) {
                                            Toast.makeText(getApplicationContext(),"d",Toast.LENGTH_SHORT).show();
                                            if ((rsnapshot.child("cal").child("time").child("hours").getValue(int.class)) >= h) {
                                                Toast.makeText(getApplicationContext(),"h",Toast.LENGTH_SHORT).show();
                                                if ((rsnapshot.child("cal").child("time").child("minutes").getValue(int.class)) >= m) {
                                                    Toast.makeText(getApplicationContext(),"m",Toast.LENGTH_SHORT).show();
                                                    flag=0;
                                                }
                                                else {
                                                    flag=1;
                                                }
                                            }
                                            else {
                                                flag=1;
                                            }
                                        }
                                        else {
                                            flag=1;
                                        }
                                    }
                                    else {
                                        flag=1;
                                    }
                                }
                                else {
                                    flag=1;
                                }
                                //Toast.makeText(getApplicationContext(),Integer.toString(flag),Toast.LENGTH_SHORT).show();
                                if (flag==0){
                                    user_dis u = new user_dis(rsnapshot.child("request").getValue(String.class), rsnapshot.child("price").getValue(String.class), rsnapshot.child("tip").getValue(String.class), rsnapshot.child("address").getValue(String.class), rsnapshot.child("cal").child("time").child("date").getValue(int.class).toString() + "-" + rsnapshot.child("cal").child("time").child("month").getValue(int.class).toString() + "-" + (rsnapshot.child("cal").child("time").child("year").getValue(int.class) + 1900) + "", rsnapshot.child("cal").child("time").child("hours").getValue(int.class).toString() + ":" + rsnapshot.child("cal").child("time").child("minutes").getValue(int.class).toString(), rsnapshot.child("city").getValue(String.class), rsnapshot.child("makeid").getValue(String.class), rsnapshot.child("reqid").getValue(String.class), rsnapshot.child("accid").getValue(String.class), rsnapshot.child("status").getValue(int.class));
                                    currentItems.add(u);
                                }
                                else if (flag==1){
                                    //Toast.makeText(getApplicationContext(),"Expired",Toast.LENGTH_SHORT).show();
                                    myRef.child(usnapshot.getKey()).child(rsnapshot.getKey()).child("status").setValue(1);
                                }
                            }

                        }
                    }
                    mAdapter = new RecyclerAdapter(Home.this, currentItems);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
