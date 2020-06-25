package com.openclassrooms.realestatemanager.data.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.repositories.EstateRepository;

import java.util.List;

public class EstateViewModel extends AndroidViewModel {
    private EstateRepository repository;
    private LiveData<Estate> estate;
    private LiveData<List<Estate>> allEstates;

    public EstateViewModel(@NonNull Application application) {
        super(application);
        repository = new EstateRepository(application);
        allEstates = repository.getAllEstates();
    }

    public void insert(Estate estate) {
        repository.insert(estate);
    }

    public void update(Estate estate) {
        repository.update(estate);
    }

    public void delete(Estate estate) {
        repository.delete(estate);
    }

    public void deleteAllEstates() {
        repository.deleteAllEstates();
    }

    public LiveData<Estate> getEstate(long id) { return repository.getEstate(id); }

    public LiveData<List<Estate>> getAllEstates() {
        return allEstates;
    }
}
