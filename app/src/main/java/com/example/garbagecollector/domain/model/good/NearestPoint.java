package com.example.garbagecollector.domain.model.good;

import java.util.List;

import com.example.garbagecollector.domain.model.City;
import com.example.garbagecollector.domain.model.TrashType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class NearestPoint {

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
private String photo;
@SerializedName("trashTypes")
@Expose
private List<TrashType> trashTypes = null;
@SerializedName("info")
@Expose
private String info;
@SerializedName("latitude")
@Expose
private String latitude;
@SerializedName("longitude")
@Expose
private String longitude;
@SerializedName("distance")
@Expose
private String distance;
@SerializedName("rating")
@Expose
private Double rating;
@SerializedName("comments")
@Expose
private List<Object> comments = null;
@SerializedName("openHours")
@Expose
private String openHours;
@SerializedName("full")
@Expose
private Boolean full;

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

public String getPhoto() {
return photo;
}

public void setPhoto(String photo) {
this.photo = photo;
}

public List<TrashType> getTrashTypes() {
return trashTypes;
}

public void setTrashTypes(List<TrashType> trashTypes) {
this.trashTypes = trashTypes;
}

public String getInfo() {
return info;
}

public void setInfo(String info) {
this.info = info;
}

public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
}

public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}

public String getDistance() {
return distance;
}

public void setDistance(String distance) {
this.distance = distance;
}

public Double getRating() {
return rating;
}

public void setRating(Double rating) {
this.rating = rating;
}

public List<Object> getComments() {
return comments;
}

public void setComments(List<Object> comments) {
this.comments = comments;
}

public String getOpenHours() {
return openHours;
}

public void setOpenHours(String openHours) {
this.openHours = openHours;
}

public Boolean getFull() {
return full;
}

public void setFull(Boolean full) {
this.full = full;
}

}