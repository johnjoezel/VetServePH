    package com.example.vetserveph;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.vetserveph.Others.Constants;
import com.example.vetserveph.Others.SharedPrefManager;
import com.example.vetserveph.PetOwner.Login;
import com.example.vetserveph.PetOwner.personalInformation;
import com.example.vetserveph.PetOwner.ownerDashboard;

import org.json.JSONException;
import org.json.JSONObject;


    public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            startActivity(new Intent(this, Login.class));
        }
    }
