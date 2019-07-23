package com.example.vetserveph.Others;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ShowAlert {
    public static void showAlert(Context context, String msg){
        try{
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Alert");
        alertDialog.setCancelable(false);
        alertDialog.setMessage(msg);
        alertDialog.setNegativeButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

            alertDialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
