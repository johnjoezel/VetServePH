package com.example.vetserveph.PetOwner;

//import android.support.v4.widget.DrawerLayout;
//import android.support.v7.app.ActionBar;
//import android.support.v7.app.ActionBarDrawerToggle;
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.MenuItem;
//import android.widget.TextView;
//
//public class ownerDashboard extends AppCompatActivity {
//    private DrawerLayout mDrawerLayout;
//    private ActionBarDrawerToggle mToggle;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_owner_dashboard);
//        mDrawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
//        mToggle=new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if(mToggle.onOptionsItemSelected(item)){
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.vetserveph.R;
import com.example.vetserveph.locateClinic;

public class ownerDashboard extends AppCompatActivity {
       // implements NavigationView.OnNavigationItemSelectedListener  {
    ImageView clinicfinder, forpets;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_dashboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        clinicfinder = findViewById(R.id.clinicfinder);
        clinicfinder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ownerDashboard.this, locateClinic.class);
                startActivity(intent);
            }
        });

        forpets = findViewById(R.id.forpets);
        forpets.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ownerDashboard.this, petListView.class);
                startActivity(intent);
            }
        });
//        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    //@SuppressWarnings("StatementWithEmptyBody")
//    @Override
//    public boolean onNavigationItemSelected(MenuItem item) {
//        // Handle navigation view item clicks here.
//        int id = item.getItemId();
//        FragmentManager fragmentManager = getFragmentManager();
//
//        if (id == R.id.nav_first_layout) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new FirstFragment())
//                    .commit();
//        } else if (id == R.id.nav_second_layout) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new SecondFragment())
//                    .commit();
//        } else if (id == R.id.nav_third_layout) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new ThirdFragment())
//                    .commit();
//        } else if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {
//
//        }
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
//        return true;
//    }
}
