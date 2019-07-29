package com.example.vetserveph.PetOwner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vetserveph.Others.SharedPrefManager;
import com.example.vetserveph.R;

public class ownerDashboardFragment extends Fragment implements View.OnClickListener {

    ImageView forpets;
    TextView txtUsername;
    public ownerDashboardFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ownerdashboard_fragment, container, false);
        forpets = view.findViewById(R.id.forpets);
        forpets.setOnClickListener(this);

        txtUsername = view.findViewById(R.id.displayusername);
        txtUsername.setText(SharedPrefManager.getInstance(getContext()).getKeyUsername());
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.i("atay", "onClick: " + view);
        startActivity(new Intent(getContext(), petListView.class));
    }
}
