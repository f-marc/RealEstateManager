package com.openclassrooms.realestatemanager.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.openclassrooms.realestatemanager.repositories.EstateDataRepository;

import java.util.List;
import java.util.concurrent.Executor;


public class EstateViewModel extends ViewModel {

    // REPOSITORIES
    private final EstateDataRepository estateDataSource;
    private final Executor executor;

    // DATA
    @Nullable
    private LiveData<Estate> currentEstate;
    private LiveData<List<Estate>> currentEstates;

    public EstateViewModel(EstateDataRepository estateDataSource, Executor executor) {
        this.estateDataSource = estateDataSource;
        this.executor = executor;
    }

    public void initEstate(long estateId) {
        if (this.currentEstate != null) {
            return;
        }
        currentEstate = estateDataSource.getEstate(estateId);
    }

    public void initEstates() {
        if (this.currentEstates != null) {
            return;
        }
        currentEstates = estateDataSource.getEstates();
    }

    public LiveData<Estate> getEstate() {
        return this.currentEstate;
    }

    public LiveData<List<Estate>> getEstates() {
        return this.currentEstates;
    }

    public void createEstate(final Estate estate) {
        executor.execute(() -> estateDataSource.createEstate(estate));
    }

    public void updateEstate(final Estate estate) {
        executor.execute(() -> estateDataSource.updateEstate(estate));
    }
}
