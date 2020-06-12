package com.openclassrooms.realestatemanager.data.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;
import static com.openclassrooms.realestatemanager.data.model.Image.IMAGE_TABLE_NAME;


@Entity(tableName = IMAGE_TABLE_NAME,
        foreignKeys = @ForeignKey(entity = Estate.class,
        parentColumns = "id",
        childColumns = "estateId",
        onDelete = CASCADE))
public class Image {

    public static final String IMAGE_TABLE_NAME = "image_table";

    private long estateId;
    @NonNull
    @PrimaryKey
    private String imageId;

    public Image(long estateId, String imageId) {
        this.estateId = estateId;
        this.imageId = imageId;
    }

    // --- GETTER ---
    public long getEstateId() { return estateId; }
    public String getImageId() { return imageId; }

    // --- SETTER ---
    public void setEstateId(long estateId) { this.estateId = estateId; }
    public void setImageId(String imageId) { this.imageId = imageId; }

}
