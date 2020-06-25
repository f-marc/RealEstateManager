package com.openclassrooms.realestatemanager.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.openclassrooms.realestatemanager.data.model.Estate;

import java.util.List;

@Dao
public interface EstateDao {

    @Insert
    void insert(Estate estate);

    @Update
    void update(Estate estate);

    @Delete
    void delete(Estate estate);

    @Query("DELETE FROM estate_table")
    void deleteAllEstates();

    @Query("SELECT * FROM estate_table WHERE id =:id")
    LiveData<Estate> getEstate(long id);

    @Query("SELECT * FROM estate_table ORDER BY price DESC")
    LiveData<List<Estate>> getAllEstates();
}
