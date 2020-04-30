package com.openclassrooms.realestatemanager.features.main;


import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.features.add.AddEstateActivity;

import java.util.List;


public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public static final int ADD_ESTATE_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.fragment_main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton buttonAddEstate = view.findViewById(R.id.button_add_estate);
        buttonAddEstate.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), AddEstateActivity.class);
            startActivityForResult(intent, ADD_ESTATE_REQUEST);
        });

        final EstateAdapter adapter = new EstateAdapter();
        recyclerView.setAdapter(adapter);

        EstateViewModel estateViewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getActivity().getApplication()))
                .get(EstateViewModel.class);
        estateViewModel.getAllEstates().observe(this, new Observer<List<Estate>>() {
            @Override
            public void onChanged(@Nullable List<Estate> estates) {
                adapter.setEstates(estates);
            }
        });

        return view;
    }

}