package com.openclassrooms.realestatemanager.features.main;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.RequestManager;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.model.Estate;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Estate> estateList;
    private Context context;
    private RequestManager glide;

    public MainAdapter(Context context, RequestManager glide) {
        this.context = context;
        this.glide = glide;
    }

    public void setEstate(List<Estate> estateList) {
        this.estateList = estateList;
    }

    public Estate getEstate(int position) { return this.estateList.get(position); }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_main_item, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder viewHolder, int position) {
        final Estate estate = estateList.get(position);

        String image = estate.getImage();
        String type = estate.getType();
        String location = estate.getAddress();
        int price = estate.getPrice();

        viewHolder.updateWithEstate(image, type, location, price, this.glide);
    }

    @Override
    public int getItemCount(){
        if (estateList == null) {
            return 0;
        }
        return estateList.size();
    }
}

