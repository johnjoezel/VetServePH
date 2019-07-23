package com.example.vetserveph.PetOwner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.vetserveph.MainActivity;
import com.example.vetserveph.Others.CallBacks;
import com.example.vetserveph.Others.ShowAlert;
import com.example.vetserveph.Others.Validation;
import com.example.vetserveph.R;
import com.example.vetserveph.Requests.GetRegistrationRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class accountInformation extends AppCompatActivity implements View.OnClickListener{

    private EditText txtusername,txtpassword, txtcpassword, txtemail;
    private Button btnRegister;
    private Intent intent;
    private ArrayList<String> data;
    private ImageView imgProfpic;
    private int IMG_REQUEST= 1;
    private Bitmap bitmap;
    Validation validation= new Validation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information);
        setTitle("Registration");

//        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
//            startActivity(new Intent(this, MainActivity.class));
//        }
        intent= getIntent();
        data= intent.getStringArrayListExtra("data");
        txtemail= findViewById(R.id.txtemail);
        txtusername= findViewById(R.id.txtusername);
        txtpassword= findViewById(R.id.txtpassword);
        txtcpassword= findViewById(R.id.txtcpassword);
        imgProfpic= findViewById(R.id.imgProfpic);


        btnRegister= findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
        imgProfpic.setOnClickListener(this);
    }

//    @Override
//    protected void onResume() {
//        if(SharedPrefManager.getInstance(this).isLoggedIn()) {
//            startActivity(new Intent(this, MainActivity.class));
//        }
//        super.onResume();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:

                if(bitmap!=null){

                    Boolean testUsername = validation.validateUsername(txtusername);
                    Boolean testPassword = validation.validatePassword(txtpassword);
                    if (testUsername && testPassword && samePassword()) {
                        data.add(txtemail.getText().toString());
                        data.add(imageToString(bitmap));
                        Log.i("test","nakasulod ko");
                        registerUser(txtusername.getText().toString().trim(),txtpassword.getText().toString().trim());
                    }
                }else{
                    ShowAlert.showAlert(this,"Please provide a photo");
//                    Toast.makeText(this, "Please choose a photo", Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.imgProfpic:
                selectImage();
        }
    }

    public void selectImage(){
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private Boolean samePassword(){
        Boolean samePass= txtpassword.getText().toString().trim().equals(txtcpassword.getText().toString().trim())?true:false;
        if(samePass==false){
            txtcpassword.setError("Password mismatch");
        }
        return samePass;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==IMG_REQUEST && resultCode==RESULT_OK && data!=null){
            Uri path= data.getData();
            try {
                bitmap= MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgProfpic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
    private String imageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream= new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imgBytes= byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    private void registerUser(String username, String password){
        GetRegistrationRequest.registerUser(this, new CallBacks() {
            @Override
            public void onSuccess(String response) throws JSONException {
                Log.i("response", response + "  ///asdfasdf");
                if(response!=null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        if (jsonObject.getBoolean("error")) {
                            ShowAlert.showAlert(accountInformation.this, jsonObject.getString("msg"));
                            txtusername.setText("");
                        } else {
                            startActivity(new Intent(getApplicationContext(), Login.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    ShowAlert.showAlert(accountInformation.this,"Something went wrong, please try again kuno.");
//                    Toast.makeText(UserRegistration.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        },data, username, password);
    }
}
