package com.openclassrooms.realestatemanager.features.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.EstateHolder> {
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
        DecimalFormat df = new DecimalFormat("#,###");
        int price = Integer.parseInt(currentEstate.getPrice());
        holder.textViewType.setText(currentEstate.getType());
        holder.textViewAddress.setText(currentEstate.getAddress());
        holder.textViewPrice.setText("$" + df.format(price)); // FICHIER LANGUES
    }

    @Override
    public int getItemCount() {
        return estates.size();
    }

    public void setEstates(List<Estate> estates) {
        this.estates = estates;
        notifyDataSetChanged();
    }

    public Estate getEstateAt(int position) {
        return estates.get(position);
    }

    class EstateHolder extends RecyclerView.ViewHolder {
        private TextView textViewType;
        private TextView textViewAddress;
        private TextView textViewPrice;

        public EstateHolder(View itemView) {
            super(itemView);
            textViewType = itemView.findViewById(R.id.estate_item_type);
            textViewAddress = itemView.findViewById(R.id.estate_item_address);
            textViewPrice = itemView.findViewById(R.id.estate_item_price);
        }
    }
}

