package com.openclassrooms.realestatemanager.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity
public class Estate {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String image;
    private String type;
    private int price;
    private String address;
    private String surface;
    private int rooms;
    private String interest;
    private String agent;
    private String description;
    private String entryDate;
    private String soldDate;
    private Boolean isSold;

    public Estate() { }

    /*public Estate(String image, String type, int price, String address, String surface, String agent, String entryDate) {
        this.image = image;
        this.type = type;
        this.price = price;
        this.address = address;
        this.surface = surface;
        this.agent = agent;
        this.entryDate = entryDate;
        this.isSold = false;
    }*/

    public Estate(String image, String type, int price, String address) {
        this.image = image;
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
    public String getImage() { return image; }
    public String getType() { return type; }
    public int getPrice() { return price; }
    public String getAddress() { return address; }
    public String getSurface() { return surface; }
    public int getRooms() { return rooms; }
    public String getInterest() { return interest; }
    public String getAgent() { return agent; }
    public String getDescription() { return description; }
    public String getEntryDate() { return entryDate; }
    public String getSoldDate() { return soldDate; }
    public Boolean getSold() { return isSold; }

    // --- SETTER ---
    public void setId(long id) { this.id = id; }
    public void setImage(String image) { this.image = image; }
    public void setType(String type) { this.type = type; }
    public void setPrice(int price) { this.price = price; }
    public void setAddress(String address) { this.address = address; }
    public void setSurface(String surface) { this.surface = surface; }
    public void setRooms(int rooms) { this.rooms = rooms; }
    public void setInterest(String interest) { this.interest = interest; }
    public void setAgent(String agent) { this.agent = agent; }
    public void setDescription(String description) { this.description = description; }
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }
    public void setSoldDate(String soldDate) { this.soldDate = soldDate; }
    public void setSold(Boolean sold) { isSold = sold; }
}
