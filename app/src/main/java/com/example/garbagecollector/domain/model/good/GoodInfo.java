package com.example.garbagecollector.domain.model.good;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class GoodInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("barCode")
@Expose
private String barCode;
@SerializedName("image")
@Expose
private String image;
@SerializedName("markings")
@Expose
private List<Marking> markings = null;

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

public String getBarCode() {
return barCode;
}

public void setBarCode(String barCode) {
this.barCode = barCode;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public List<Marking> getMarkings() {
return markings;
}

public void setMarkings(List<Marking> markings) {
this.markings = markings;
}

}