package com.openclassrooms.realestatemanager.data.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.openclassrooms.realestatemanager.data.model.Estate;

import java.util.List;

@Dao
public interface EstateDao {

    @Query("SELECT * FROM Estate WHERE id = :estateId")
    LiveData<Estate> getEstate(long estateId);

    @Query("SELECT * FROM Estate")
    LiveData<List<Estate>> getEstates();

    @Insert
    void createEstate(Estate estate);

    @Update
    void updateEstate(Estate estate);
}
