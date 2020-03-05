package com.openclassrooms.realestatemanager.features.main;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.openclassrooms.realestatemanager.R;
import com.openclassrooms.realestatemanager.data.model.Estate;
import com.openclassrooms.realestatemanager.data.model.EstateViewModel;
import com.openclassrooms.realestatemanager.injections.Injection;
import com.openclassrooms.realestatemanager.injections.ViewModelFactory;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.BindView;

public class MainFragment extends Fragment {

    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;

    private EstateViewModel estateViewModel;
    private MainAdapter adapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        this.configureRecyclerView();
        this.configureViewModel();
        this.getEstates();

        return view;
    }


    // -------------------
    // CONFIGURE
    // -------------------

    // Configure RecyclerView
    private void configureRecyclerView(){
        this.adapter = new MainAdapter(getActivity(), Glide.with(this));
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // Configuring ViewModel
    private void configureViewModel(){
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(getActivity());
        this.estateViewModel = ViewModelProviders.of(this, mViewModelFactory).get(EstateViewModel.class);
        this.estateViewModel.initEstates();
    }

    // ---

    // Get all items for a user
    private void getEstates(){
        this.estateViewModel.getEstates().observe(this, this::updateEstatesList);
    }

    // Update the list of items
    private void updateEstatesList(List<Estate> estateList){
        this.adapter.updateData(estateList);
    }

}
