package com.openclassrooms.realestatemanager.features.detail;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.database.EstateDao;
import com.openclassrooms.realestatemanager.data.database.EstateDatabase;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;

import java.util.List;

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

    private Estate estate;
    private EstateViewModel estateViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        ButterKnife.bind(this, view);

        assert getArguments() != null;
        long id = getArguments().getLong("id");
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

}
