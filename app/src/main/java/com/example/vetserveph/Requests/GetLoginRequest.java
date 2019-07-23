package com.example.vetserveph.Requests;

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

import java.util.HashMap;
import java.util.Map;

public class GetLoginRequest {
//    private static ProgressDialog progressDialog;


    public static void login(final Context context, final CallBacks callBacks, final String username, final String password){
//        progressDialog= new ProgressDialog(context);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.show();

        StringRequest stringRequest= new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        progressDialog.dismiss();
                        try {
                            callBacks.onSuccess(response);
                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        progressDialog.dismiss();
                        try {
                            callBacks.onSuccess(null);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params= new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return  params;
            }
        };
        RequestHandler.getInstance(context).addToRequestQueue(stringRequest);

    }

}
