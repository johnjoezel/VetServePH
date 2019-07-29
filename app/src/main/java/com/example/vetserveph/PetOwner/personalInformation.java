package com.example.vetserveph.PetOwner;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.vetserveph.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class personalInformation extends AppCompatActivity implements View.OnClickListener{

    Button btncontinue;
    DatePickerDialog dialog;
    private DatePickerDialog.OnDateSetListener mDateListener;
    private EditText txtfirstname, txtlastname, txtmname, txtcontact, txtBday;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        txtfirstname = findViewById(R.id.txtfirstname);
        txtmname = findViewById(R.id.txtmname);
        txtlastname = findViewById(R.id.txtlastname);
        txtcontact = findViewById(R.id.txtcontact);
        txtBday= findViewById(R.id.dateofbirth);
        btncontinue = findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(this);
        txtBday.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.dateofbirth:
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                dialog = new DatePickerDialog(
                        personalInformation.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Log.d("datekuno","date has been set");
                mDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;

                        String date = month + "/" + day + "/" + year;
                        txtBday.setText(date);
                    }
                };

                break;
            case R.id.btncontinue:
                String firstname= txtfirstname.getText().toString().trim();
                String middlename= txtmname.getText().toString().trim();
                String lastname= txtlastname.getText().toString().trim();
                String birthday= txtBday.getText().toString().trim();
                String contact = txtcontact.getText().toString().trim();
                ArrayList<String> data = new ArrayList<String>();
                data.add(firstname);
                data.add(middlename);
                data.add(lastname);
                data.add(birthday);
                data.add(contact);
                Intent intent = new Intent(personalInformation.this, accountInformation.class);
                intent.putStringArrayListExtra("data", data);
                startActivity(intent);
                break;
        }
    }

}
