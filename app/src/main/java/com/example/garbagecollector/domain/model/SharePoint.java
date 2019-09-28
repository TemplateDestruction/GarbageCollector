package com.example.garbagecollector.domain.model;


import androidx.annotation.Nullable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SharePoint {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private City city;
    @SerializedName("photo")
    @Expose
    private Object photo;
    @SerializedName("trashTypes")
    @Expose
    private List<TrashType> trashTypes = null;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Object getPhoto() {
        return photo;
    }

    public void setPhoto(Object photo) {
        this.photo = photo;
    }

    public List<TrashType> getTrashTypes() {
        return trashTypes;
    }

    public void setTrashTypes(List<TrashType> trashTypes) {
        this.trashTypes = trashTypes;
    }

    @Nullable
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
