package com.openclassrooms.realestatemanager.features.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;

import java.util.ArrayList;
import java.util.List;

public class EstateAdapter extends RecyclerView.Adapter<EstateAdapter.EstateHolder> {
    private List<Estate> estates = new ArrayList<>();

    @NonNull
    @Override
    public EstateHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.estate_item, parent, false);
        return new EstateHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EstateHolder holder, int position) {
        Estate currentEstate = estates.get(position);
        holder.textViewType.setText(currentEstate.getType());
        holder.textViewAddress.setText(currentEstate.getAddress());
        holder.textViewPrice.setText(String.valueOf(currentEstate.getPrice()));
    }

    @Override
    public int getItemCount() {
        return estates.size();
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
        notifyDataSetChanged();
    }

    class EstateHolder extends RecyclerView.ViewHolder {
        private TextView textViewType;
        private TextView textViewAddress;
        private TextView textViewPrice;

        public EstateHolder(View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.fragment_main_item_type);
            textViewAddress = itemView.findViewById(R.id.fragment_main_item_location);
            textViewPrice = itemView.findViewById(R.id.fragment_main_item_price);
        }
    }
}

