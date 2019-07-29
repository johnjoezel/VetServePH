package com.example.vetserveph.PetOwner;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vetserveph.Others.CallBacks;
import com.example.vetserveph.Others.SharedPrefManager;
import com.example.vetserveph.Others.ShowAlert;
import com.example.vetserveph.Others.Validation;
import com.example.vetserveph.R;
import com.example.vetserveph.Requests.GetLoginRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener{

    ImageView gg;
    EditText txtusername, txtpassword;
    Button btnlogin;
    TextView btnsignup;
    Validation validation = new Validation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername= findViewById(R.id.txtusername);
        txtpassword= findViewById(R.id.txtpassword);
        btnlogin= findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener(this);
        btnsignup = findViewById(R.id.signup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, personalInformation.class);
                startActivity(intent);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        if (SharedPrefManager.getInstance(this).getKeyUsername() != null) {
            startActivity(new Intent(this, MainActivity.class));
            return;
        }

    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnlogin:
                if (validation.validateNormalInput(txtusername) && validation.validateNormalInput(txtpassword)) {
                    final String username = txtusername.getText().toString().trim();
                    final String password = txtpassword.getText().toString().trim();
                    login(username, password);
                }
                break;
        }
    }
    private void login(final String username, final String password) {
        final ProgressDialog progressDialog;
        progressDialog= new ProgressDialog(Login.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        try{
            GetLoginRequest.login(this, new CallBacks() {
                @Override
                public void onSuccess(String response) throws JSONException {
                    progressDialog.dismiss();
                    if (response != null) {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("error")) {
                            ShowAlert.showAlert(Login.this,jsonObject.getString("msg"));
                        } else {
                            SharedPrefManager.getInstance(Login.this).userLogin(username);
                            try{
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    } else {
                        ShowAlert.showAlert(Login.this, "Something went wrong. atay" );
//                        Toast.makeText(Login.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }, username, password);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
