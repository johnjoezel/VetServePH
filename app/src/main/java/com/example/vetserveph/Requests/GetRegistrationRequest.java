package com.example.vetserveph.Requests;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.vetserveph.Others.CallBacks;
import com.example.vetserveph.Others.Constants;
import com.example.vetserveph.Others.RequestHandler;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GetRegistrationRequest {
    private static ProgressDialog progressDialog;


    public static void registerUser(final Context context, final CallBacks callBacks, ArrayList<String> data, final String username, final String password) {
        final String
                fname = data.get(0),
                mname = data.get(1),
                lname = data.get(2),
                bday = data.get(3),
                contact = data.get(4),
                email = data.get(5),
                imgtoStringBitmap= data.get(6);

        Log.i("taghttppost", "" + bday);
        progressDialog = new ProgressDialog(context);
        //progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering");
        progressDialog.show();
        String URL = Constants.URL_REGISTER_USER;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();
                try {
                    callBacks.onSuccess(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                try {
                    callBacks.onSuccess(null);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("firstname", fname);
                params.put("middlename", mname);
                params.put("lastname", lname);
                params.put("birthdate", bday);
                params.put("contact", contact);
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);
                params.put("image", imgtoStringBitmap);

                return params;
            }
        }   ;
        RequestHandler.getInstance((context)).addToRequestQueue(stringRequest);
    }
}
