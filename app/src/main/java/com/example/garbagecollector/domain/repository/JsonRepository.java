package com.example.garbagecollector.domain.repository;


import androidx.annotation.NonNull;

import com.example.garbagecollector.domain.model.SharePoint;
import com.example.garbagecollector.domain.model.good.GoodInfo;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import okhttp3.RequestBody;


public interface JsonRepository {

    @NonNull
    Observable<List<SharePoint>> getSeparateCollectionPoints();

    @NonNull
    Observable<SharePoint> getSeparateCollectionPointById(int id);

    @NonNull
    Observable<GoodInfo> getGoodInfo(String id);
}
