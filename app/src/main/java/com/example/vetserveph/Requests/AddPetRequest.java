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

public class AddPetRequest {
    private static ProgressDialog progressDialog;


    public static void addPet(final Context context, final CallBacks callBacks, ArrayList<String> data) {
        final String
                petname = data.get(0),
                petspecies = data.get(1),
                petbreed = data.get(2),
                petgender = data.get(3),
                petbdate = data.get(4);

        Log.i("taghttppost", "" + petspecies);
        progressDialog = new ProgressDialog(context);
        //progressDialog.setCancelable(false);
        progressDialog.setMessage("Registering");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_ADD_PET, new Response.Listener<String>() {
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
                params.put("petname", petname);
                params.put("petspecies", petspecies);
                params.put("petbreed", petbreed);
                params.put("petgender", petgender);
                params.put("petdob", petbdate);
                return params;
            }
        }   ;
        RequestHandler.getInstance((context)).addToRequestQueue(stringRequest);
    }
}
