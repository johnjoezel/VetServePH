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

public class GetPetDetailsRequest {
    private static ProgressDialog progressDialog;


    public static void getPetDetails(final Context context, final CallBacks callBacks, String petowner_username) {
        final String
                petownerUsername = petowner_username;


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LIST_OF_PETS, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                params.put("petowner_username", petownerUsername);
                return params;
            }
        }   ;
        RequestHandler.getInstance((context)).addToRequestQueue(stringRequest);
    }
}