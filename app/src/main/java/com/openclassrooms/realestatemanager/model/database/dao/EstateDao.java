package com.openclassrooms.realestatemanager.model.database.dao;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.openclassrooms.realestatemanager.model.Estate;

@Dao
public interface EstateDao {

    @Query("SELECT * FROM Estate WHERE id = :estateId")
    LiveData<Estate> getEstate(long estateId);

    @Insert
    void createEstate(Estate estate);

    @Update
    int updateEstate(Estate estate);
}
