package com.openclassrooms.realestatemanager.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.openclassrooms.realestatemanager.data.model.Image;

import java.util.List;


@Dao
public interface ImageDao {

    @Insert
    void insert(Image image);

    @Query("SELECT * FROM image_table WHERE estateId = :estateId")
    LiveData<List<Image>> getImages(long estateId);

    @Query("DELETE FROM image_table WHERE imageId = :imageId")
    int deleteImage(long imageId);
}
