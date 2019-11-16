package com.example.garbagecollector.domain.repository;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.garbagecollector.domain.api.StandardApiFactory;
import com.example.garbagecollector.domain.model.SharePoint;
import com.example.garbagecollector.domain.model.good.GoodInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DefaultJsonRepository implements JsonRepository {



    @NonNull
    @Override
    public Observable<List<SharePoint>> getSeparateCollectionPoints() {
        return StandardApiFactory
                .getJsonService()
                .getSeparateCollectionPoints()
                .flatMap(new Function<List<SharePoint>, ObservableSource<List<SharePoint>>>() {
                    @Override
                    public ObservableSource<List<SharePoint>> apply(List<SharePoint> sharePoints) throws Exception {
                        Log.e("JsonRepository:", "getSeparateCollection: " + sharePoints.size());
                        return Observable.just(sharePoints);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<SharePoint> getSeparateCollectionPointById(String id) {
        return StandardApiFactory
                .getJsonService()
                .getSeparateCollectionPointById(id)
                .flatMap(new Function<SharePoint, ObservableSource<SharePoint>>() {
                    @Override
                    public ObservableSource<SharePoint> apply(SharePoint sharePoint) throws Exception {

                        return Observable.just(sharePoint);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @NonNull
    @Override
    public Observable<GoodInfo> getGoodInfo(String id, String lat, String lng) {
        return StandardApiFactory
                .getJsonService()
                .getGoodInfo(id, lat, lng)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
