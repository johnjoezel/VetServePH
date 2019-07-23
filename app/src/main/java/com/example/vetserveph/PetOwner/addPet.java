package com.example.vetserveph.PetOwner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.vetserveph.Others.CallBacks;
import com.example.vetserveph.Others.ShowAlert;
import com.example.vetserveph.R;
import com.example.vetserveph.Requests.AddPetRequest;
import com.example.vetserveph.Requests.GetRegistrationRequest;
import com.example.vetserveph.Requests.GetSpinnerSpeciesItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;


public class addPet extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    ArrayAdapter genderadapter, speciesadapter, breedsadapter;
    ArrayList<String> specieslist = new ArrayList<String>();
    DatePickerDialog dialog;
    Spinner spnBreeds, spnrGender, spnrSpecies;
    Button btnaddpet;
    EditText petdateofbirth, txtpetname;
    Typeface tfavv;
    String petbreed, petspecies, petgender;
    private ArrayList<String> data = new ArrayList<>();
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pet);


        spnrSpecies =  findViewById(R.id.spnrSpecies);
        spnBreeds =  findViewById(R.id.spnrBreed);
        spnrGender = findViewById(R.id.spnrGender);
        btnaddpet = findViewById(R.id.btnaddpet);
        txtpetname = findViewById(R.id.txtpetname);
        petdateofbirth = findViewById(R.id.petdateofbirth);
        petdateofbirth.setOnClickListener(this);
        btnaddpet.setOnClickListener(this);
        spnrGender.setOnItemSelectedListener(this);
        spnrSpecies.setOnItemSelectedListener(this);
        spnBreeds.setOnItemSelectedListener(this);
        breedsadapter = ArrayAdapter.createFromResource(this, R.array.spinner_breed_items, R.layout.spinner_item);
        breedsadapter.setDropDownViewResource(R.layout.spinner_adapter);
        spnBreeds.setAdapter(breedsadapter);
        genderadapter = ArrayAdapter.createFromResource(this, R.array.spinner_gender_items, R.layout.spinner_item);
        genderadapter.setDropDownViewResource(R.layout.spinner_adapter);
        spnrGender.setAdapter(genderadapter);
        try{
            GetSpinnerSpeciesItems.getSpinnerItems(this, new CallBacks() {
                @Override
                public void onSuccess(String response) throws JSONException {
                    if (response != null) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonobject = jsonArray.getJSONObject(i);
                                specieslist.add(jsonobject.getString("species_name"));
                                speciesadapter = new ArrayAdapter<String>(addPet.this,
                                        R.layout.spinner_item, specieslist);
                                speciesadapter.setDropDownViewResource(R.layout.spinner_adapter);
                                spnrSpecies.setAdapter(speciesadapter);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        if(parent.getId() == R.id.spnrSpecies) {
            if(position!=-1) {
                petspecies = parent.getItemAtPosition(position).toString();
                Log.i("itemitem", petspecies);
            }
        }
        else if(parent.getId() == R.id.spnrBreed) {
            if(position!=-1) {
                petbreed = parent.getItemAtPosition(position).toString();
                Log.i("itemitem", petbreed);
            }
        }
        else if(parent.getId() == R.id.spnrGender) {
            if(position!=-1) {
                petgender = parent.getItemAtPosition(position).toString();
                Log.i("itemitem", petgender);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.petdateofbirth:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                dialog = new DatePickerDialog(
                        addPet.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Log.d("datekuno","date has been set");
                mDateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;

                        String date = month + "/" + day + "/" + year;
                        petdateofbirth.setText(date);
                    }
                };
                break;
            case R.id.btnaddpet:
                String petname= txtpetname.getText().toString().trim();
                String finalpetspecies= petspecies.trim();
                String finalpetbreed= petbreed.trim();
                String finalpetgender= petgender.trim();
                String petbirthday= petdateofbirth.getText().toString().trim();
                data.add(petname);
                data.add(finalpetspecies);
                data.add(finalpetbreed);
                data.add(finalpetgender);
                data.add(petbirthday);
                addPet();
                break;
        }
    }
    private void addPet(){
        AddPetRequest.addPet(this, new CallBacks() {
            @Override
            public void onSuccess(String response) throws JSONException {
                Log.i("response", response + "  ///asdfasdf");
                if(response!=null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("error")) {
                            ShowAlert.showAlert(addPet.this, jsonObject.getString("msg"));
                        } else {
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    ShowAlert.showAlert(addPet.this,"Something went wrong, please try again kuno.");
//                    Toast.makeText(UserRegistration.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        },data);
    }
}
