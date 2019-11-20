package com.example.garbagecollector.domain.model.good;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Marking implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("description")
@Expose
private String description;
@SerializedName("samples")
@Expose
private String samples;
@SerializedName("image")
@Expose
private String image;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getSamples() {
return samples;
}

public void setSamples(String samples) {
this.samples = samples;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

}