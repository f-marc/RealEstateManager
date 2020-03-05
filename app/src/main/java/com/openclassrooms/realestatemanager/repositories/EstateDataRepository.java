package com.openclassrooms.realestatemanager.repositories;

import android.arch.lifecycle.LiveData;

import com.openclassrooms.realestatemanager.data.database.EstateDao;
import com.openclassrooms.realestatemanager.data.model.Estate;

import java.util.List;


public class EstateDataRepository {

    private final EstateDao estateDao;

    public EstateDataRepository(EstateDao estateDao) { this.estateDao = estateDao; }

    // --- GET ---
    public LiveData<Estate> getEstate(long estateId) { return this.estateDao.getEstate(estateId); }
    public LiveData<List<Estate>> getEstates() { return this.estateDao.getEstates(); }

    // --- CREATE ---
    public void createEstate(Estate estate){ estateDao.createEstate(estate); }

    // --- UPDATE ---
    public void updateEstate(Estate estate){ estateDao.updateEstate(estate); }
}
