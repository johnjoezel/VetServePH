package com.example.vetserveph.PetOwner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.vetserveph.Others.CallBacks;
import com.example.vetserveph.Others.SharedPrefManager;
import com.example.vetserveph.Others.ShowAlert;
import com.example.vetserveph.R;
import com.example.vetserveph.Requests.GetLoginRequest;
import com.example.vetserveph.Requests.GetPetDetailsRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class petListView extends AppCompatActivity {
    RecyclerView recyclerPet;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String petOwner;
    ArrayList<Pet> petArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list_view);
        recyclerPet = findViewById(R.id.listofpet);
        recyclerPet.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        petOwner = SharedPrefManager.getInstance(this).getKeyUsername();
        listofpets();
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

    private void listofpets(){
        try{
            GetPetDetailsRequest.getPetDetails(this, new CallBacks() {
                @Override
                public void onSuccess(String response) throws JSONException {
                    if (response != null) {
                        JSONArray jsonArray = new JSONArray(response);
                        JSONArray pet = new JSONArray();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            JSONObject jsonObject1 = jsonObject.getJSONObject("pet");
                            String pet_name = jsonObject1.getString("pet_name");
                            String pet_species = jsonObject1.getString("petSpecies_id");
                            String pet_breed = jsonObject1.getString("breed_name");
                            String pet_gender = jsonObject1.getString("pet_gender");
                            String pet_DOB = jsonObject1.getString("pet_DOB");
                            String pet_color = jsonObject1.getString("pet_color");
                            petArrayList.add(new Pet(pet_name,pet_species, pet_breed,pet_gender,pet_DOB,pet_color));
                        }
                        mAdapter = new PetListAdapter(petArrayList);
                        recyclerPet.setLayoutManager(mLayoutManager);
                        recyclerPet.setAdapter(mAdapter);
                    } else {
                        ShowAlert.showAlert(petListView.this, "Something went wrong. atay" );
//                        Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }, petOwner);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
