package com.example.garbagecollector.domain.api;

import com.example.garbagecollector.domain.model.SharePoint;
import com.example.garbagecollector.domain.model.good.GoodInfo;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonService {

    @GET("point/{id}")
    Observable<SharePoint> getSeparateCollectionPointById(@Path("id") int id);

    @GET("point/all")
    Observable<List<SharePoint>> getSeparateCollectionPoints();

    @GET("good/code/{barCode}/nearest")
    Observable<GoodInfo> getGoodInfo(
            @Path("barCode") String barCode,
            @Query("latitude") String lat,
            @Query("longitude") String lng);




}
