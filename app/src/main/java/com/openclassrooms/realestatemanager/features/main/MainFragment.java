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
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.features.add.AddEstateActivity;
import com.openclassrooms.realestatemanager.utils.Utils;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.openclassrooms.realestatemanager.features.main.MainActivity.ADD_ESTATE_REQUEST;


public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private EstateViewModel estateViewModel;

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

        final MainAdapter adapter = new MainAdapter();
        recyclerView.setAdapter(adapter);

        estateViewModel = new ViewModelProvider(this,
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_ESTATE_REQUEST && resultCode == RESULT_OK) {
            String image = "";
            String type = data.getStringExtra(AddEstateActivity.EXTRA_TYPE);
            int price = data.getIntExtra(AddEstateActivity.EXTRA_PRICE, 0);
            String address = data.getStringExtra(AddEstateActivity.EXTRA_ADDRESS);
            String surface = data.getStringExtra(AddEstateActivity.EXTRA_SURFACE);
            int rooms = data.getIntExtra(AddEstateActivity.EXTRA_ROOMS, 0); // A AJOUTER
            String interest = data.getStringExtra(AddEstateActivity.EXTRA_INTEREST); // A AJOUTER
            String agent = data.getStringExtra(AddEstateActivity.EXTRA_AGENT);
            String description = data.getStringExtra(AddEstateActivity.EXTRA_DESCRIPTION); // A AJOUTER
            String date = Utils.getTodayDate();

            Estate estate = new Estate(image, type, price, address, surface, agent, date);
            estateViewModel.insert(estate);

            Toast.makeText(getContext(), "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }

}