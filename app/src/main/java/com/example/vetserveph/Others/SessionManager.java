package com.example.vetserveph.Others;

import android.content.Context;
import android.content.Intent;
import com.example.vetserveph.PetOwner.Login;
import com.example.vetserveph.PetOwner.MainActivity;

public class SessionManager {
    public static void loginRequired(Context context){
        if(SharedPrefManager.getInstance(context).getKeyUsername()==null){
            context.startActivity(new Intent(context, Login.class));
        }
    }
    public static void checkSession(Context context){
        if(SharedPrefManager.getInstance(context).getKeyUsername()!=null){
            context.startActivity(new Intent(context, MainActivity.class));
        }
    }
}