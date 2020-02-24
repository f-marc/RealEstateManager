package com.openclassrooms.realestatemanager.features.main;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.openclassrooms.realestatemanager.R;

public class MainViewHolder extends RecyclerView.ViewHolder {

    private ImageView itemImage;
    private TextView itemType, itemLocation, itemPrice;

    public MainViewHolder(View itemView) {
        super(itemView);

        itemImage = itemView.findViewById(R.id.fragment_main_item_image);
        itemType = itemView.findViewById(R.id.fragment_main_item_type);
        itemLocation = itemView.findViewById(R.id.fragment_main_item_location);
        itemPrice = itemView.findViewById(R.id.fragment_main_item_price);
    }

    public void updateWithEstate(String itemImage, String itemType, String itemLocation, int itemPrice, RequestManager glide){

        this.itemType.setText(itemType);
        this.itemLocation.setText(itemLocation);
        this.itemPrice.setText(itemPrice);

        if(!TextUtils.isEmpty(itemImage)){
            glide.load(itemImage)
                    .apply(RequestOptions.circleCropTransform())
                    .into(this.itemImage);
        }
    }

}