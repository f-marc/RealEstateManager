package com.openclassrooms.realestatemanager.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import static com.openclassrooms.realestatemanager.data.model.Estate.ESTATE_TABLE_NAME;


@Entity(tableName = ESTATE_TABLE_NAME)
public class Estate {

    public static final String ESTATE_TABLE_NAME = "estate_table";

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String thumbnail;
    private String type;
    private String price;
    private String address;
    private String surface;
    private String rooms;
    private String interest;
    private String agent;
    private String description;
    private String entryDate;
    private String soldDate;
    private Boolean isSold;

    public Estate(String thumbnail, String type, String price, String address, String surface, String agent, String entryDate) {
        this.thumbnail = thumbnail;
        this.type = type;
        this.price = price;
        this.address = address;
        this.surface = surface;
        this.agent = agent;
        this.entryDate = entryDate;
        this.isSold = false;
    }

    // --- GETTER ---
    public long getId() { return id; }
    public String getThumbnail() { return thumbnail; }
    public String getType() { return type; }
    public String getPrice() { return price; }
    public String getAddress() { return address; }
    public String getSurface() { return surface; }
    public String getRooms() { return rooms; }
    public String getInterest() { return interest; }
    public String getAgent() { return agent; }
    public String getDescription() { return description; }
    public String getEntryDate() { return entryDate; }
    public String getSoldDate() { return soldDate; }
    public Boolean getSold() { return isSold; }

    // --- SETTER ---
    public void setId(long id) { this.id = id; }
    public void setThumbnail(String thumbnail) { this.thumbnail = thumbnail; }
    public void setType(String type) { this.type = type; }
    public void setPrice(String price) { this.price = price; }
    public void setAddress(String address) { this.address = address; }
    public void setSurface(String surface) { this.surface = surface; }
    public void setRooms(String rooms) { this.rooms = rooms; }
    public void setInterest(String interest) { this.interest = interest; }
    public void setAgent(String agent) { this.agent = agent; }
    public void setDescription(String description) { this.description = description; }
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }
    public void setSoldDate(String soldDate) { this.soldDate = soldDate; }
    public void setSold(Boolean sold) { isSold = sold; }
}


