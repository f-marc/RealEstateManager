package com.openclassrooms.realestatemanager.features.detail;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    public static DetailFragment newInstance() {
        return new DetailFragment();
    }

    @BindView(R.id.fragment_detail_description_text) TextView descriptionText;
    @BindView(R.id.fragment_detail_surface_text) TextView surfaceText;
    @BindView(R.id.fragment_detail_rooms_text) TextView roomsText;
    @BindView(R.id.fragment_detail_address_text) TextView addressText;

    private EstateViewModel estateViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        long id = 0;
        if (getArguments() != null) {
            id = getArguments().getLong("id");
        }
        Log.i("testid", "" + id);

        estateViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()))
                .get(EstateViewModel.class);
        estateViewModel.getEstate(id).observe(this, estate -> {
            descriptionText.setText(estate.getRooms());
            surfaceText.setText(estate.getSurface());
            roomsText.setText(estate.getRooms());
            addressText.setText(estate.getAddress());
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment mapStaticFragment = new MapStaticFragment();
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_detail_map, mapStaticFragment).commit();
    }

}
