package com.example.vetserveph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;

public class clinicInformation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_information);

        final RatingBar ratingRatingBar = (RatingBar) findViewById(R.id.rating);
        ratingRatingBar.setRating(1);
    }
}
