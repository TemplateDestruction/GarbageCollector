package com.example.garbagecollector.domain.model.good;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.versionedparcelable.ParcelField;

import java.lang.annotation.Annotation;
import java.util.List;

import com.example.garbagecollector.domain.model.TrashType;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;


@Serializable()
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
@SerializedName("trashType")
@Expose
private TrashType trashType;
@SerializedName("nearestPoint")
@Expose
private NearestPoint nearestPoint;
@SerializedName("allCityPoints")
@Expose
private List<Point> points = null;
@SerializedName("article")
@Expose
private Article article;

//    protected GoodInfo(Parcel in) {
//        if (in.readByte() == 0) {
//            id = null;
//        } else {
//            id = in.readInt();
//        }
//        name = in.readString();
//        barCode = in.readString();
//        image = in.readString();
//        article = in.reado
//    }

//
//
//    public static final Creator<GoodInfo> CREATOR = new Creator<GoodInfo>() {
//        @Override
//        public GoodInfo createFromParcel(Parcel in) {
//            return new GoodInfo(in);
//        }
//
//        @Override
//        public GoodInfo[] newArray(int size) {
//            return new GoodInfo[size];
//        }
//    };

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

public TrashType getTrashType() {
return trashType;
}

public void setTrashType(TrashType trashType) {
this.trashType = trashType;
}

public NearestPoint getNearestPoint() {
return nearestPoint;
}

public void setNearestPoint(NearestPoint nearestPoint) {
this.nearestPoint = nearestPoint;
}

public List<Point> getPoints() {
return points;
}

public void setPoints(List<Point> points) {
this.points = points;
}

public Article getArticle() {
return article;
}

public void setArticle(Article article) {
this.article = article;
}


}