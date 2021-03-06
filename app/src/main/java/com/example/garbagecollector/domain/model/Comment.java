package com.example.garbagecollector.domain.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comment implements Serializable {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("text")
@Expose
private String text;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getText() {
return text;
}

public void setText(String text) {
this.text = text;
}

}