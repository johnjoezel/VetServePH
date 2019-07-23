package com.example.vetserveph.Others;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vetserveph.R;

import java.util.ArrayList;

public class PetListAdapter extends RecyclerView.Adapter<PetListAdapter.PetListViewHolder> {
    private ArrayList<Pet> mPetList;
    public static class PetListViewHolder extends RecyclerView.ViewHolder{
        public ImageView petPicture;
        public TextView petname, petspecies, petbreed, petgender, petage;

        public PetListViewHolder(@NonNull View itemView) {
            super(itemView);
            petPicture = itemView.findViewById(R.id.petImage);
            petname = itemView.findViewById(R.id.viewpetname);
            petbreed = itemView.findViewById(R.id.viewpetbreed);

        }
    }

    public PetListAdapter(ArrayList<Pet> petList) {
        mPetList = petList;
    }

    @NonNull
    @Override
    public PetListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.petlist_layout, parent, false);
        PetListViewHolder evh = new PetListViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull PetListViewHolder holder, int position) {
        Pet currentItem = mPetList.get(position);

//        holder.petPicture(currentItem.getImageResource());
        holder.petname.setText(currentItem.getPet_name());
        holder.petbreed.setText(currentItem.getBreed());
    }

    @Override
    public int getItemCount() {
        return mPetList.size();
    }
}
