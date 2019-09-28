package com.example.garbagecollector.domain.repository;

import androidx.annotation.NonNull;

import com.example.garbagecollector.domain.api.StandardApiFactory;
import com.example.garbagecollector.domain.model.SharePoint;
import com.example.garbagecollector.domain.model.good.GoodInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DefaultJsonRepository implements JsonRepository {



    @NonNull
    @Override
    public Observable<List<SharePoint>> getSeparateCollectionPoints() {
        return StandardApiFactory
                .getJsonService()
                .getSeparateCollectionPoints()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<SharePoint> getSeparateCollectionPointById(int id) {
        return StandardApiFactory
                .getJsonService()
                .getSeparateCollectionPointById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<GoodInfo> getGoodInfo(String id) {
        return StandardApiFactory
                .getJsonService()
                .getGoodInfo(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
