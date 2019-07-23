package com.example.vetserveph.PetOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.vetserveph.Others.Pet;
import com.example.vetserveph.Others.PetListAdapter;
import com.example.vetserveph.R;

import java.util.ArrayList;

public class petListView extends AppCompatActivity {
    RecyclerView recyclerPet;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Pet> exampleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_view);
        recyclerPet = findViewById(R.id.listofpet);
        exampleList.add(new Pet("name", "species", "breed", "gender", "age"));
        exampleList.add(new Pet("Browny", "Dog", "Bulldog", "Male", "3"));
        recyclerPet.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new PetListAdapter(exampleList);
        recyclerPet.setLayoutManager(mLayoutManager);
        recyclerPet.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(petListView.this, addPet.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
