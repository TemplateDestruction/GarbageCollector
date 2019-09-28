package com.example.garbagecollector.view.main.ui.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.garbagecollector.R;
import com.example.garbagecollector.domain.model.SharePoint;
import com.example.garbagecollector.domain.model.TrashType;
import com.example.garbagecollector.domain.repository.RepositoryProvider;
import com.example.garbagecollector.view.standard.LoadingDialog;
import com.example.garbagecollector.view.standard.LoadingView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class MapViewFragment extends Fragment {
    LoadingView dialog;
    ArrayList<LatLng> pointsCords = new ArrayList<>();
    MapView mMapView;
    private GoogleMap googleMap;
    CheckBox glassFilter;
    CheckBox paperFilter;
    ConstraintLayout glassTrashBtn;
    ConstraintLayout paperTrashBtn;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
    ArrayList<Marker> paperMarkers = new ArrayList<>();
    ArrayList<Marker> glassMarkers = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dialog = LoadingDialog.view(getFragmentManager());

        View rootView = inflater.inflate(R.layout.map_lay, container, false);
//        ensurePermissions(getActivity());
        mMapView = (MapView) rootView.findViewById(R.id.mapViewNew);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initMap();
//        Action action = this::initMap;
//        Completable completable = Completable.fromAction(action);
//        completable.subscribe(() ->
//                this.onMapInited()
//        );
    //        Completable
    //                .create(emitter -> initMap())
    //                .subscribeOn(Schedulers.io())
    //                .observeOn(AndroidSchedulers.mainThread())
    //                .subscribe(this::onMapInited, this::onError);
//        Observable<String> mapObs = Observable.create((ObservableOnSubscribe<String>) emitter ->
//                mMapView.getMapAsync(mMap -> {
//            googleMap = mMap;
////            ensurePermissions(requireActivity());
//            // For showing a move to my location button
////                googleMap.setMyLocationEnabled(true);
//
//            // For dropping a marker at a point on the Map
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//            // For zooming automatically to the location of the marker
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//        }));
//                mMapView.getMapAsync(mMap -> {
//                    googleMap = mMap;
////            ensurePermissions(requireActivity());
//                    // For showing a move to my location button
////                googleMap.setMyLocationEnabled(true);
//
//                    // For dropping a marker at a point on the Map
//                    LatLng sydney = new LatLng(-34, 151);
//                    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                    // For zooming automatically to the location of the marker
//                    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//                });

        return rootView;
    }

    private void onMapInited() {
        getTrashCollectionPoints();
        ensurePermissions(requireActivity());
    }

    private void initMap() {
        Log.e("Called map async", "initMap: ");
        mMapView.getMapAsync(mMap -> {
    googleMap = mMap;
//            ensurePermissions(requireActivity());
    // For showing a move to my location button
//                googleMap.setMyLocationEnabled(true);

    // For dropping a marker at a point on the Map
    LatLng sydney = new LatLng(-34, 151);
    googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

    // For zooming automatically to the location of the marker
    CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    getTrashCollectionPoints();
    ensurePermissions(requireActivity());
});
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        glassFilter = view.findViewById(R.id.glass_filter_btn);
        glassTrashBtn = view.findViewById(R.id.glass_trash_btn);
        paperFilter = view.findViewById(R.id.paper_filter_btn);
        paperTrashBtn = view.findViewById(R.id.paper_trash_btn);

        glassTrashBtn.setOnClickListener(view1 -> glassFilter.performClick());
        paperTrashBtn.setOnClickListener(view1 -> paperFilter.performClick());

        glassFilter.setOnCheckedChangeListener((glassFilter, checked) -> {
            if (checked) {
                glassMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                glassMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        paperFilter.setOnCheckedChangeListener((paperFilter, checked) -> {
            if (checked) {
                paperMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                paperMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
    }

    private void getTrashCollectionPoints() {
        RepositoryProvider
                .getJsonRepository()
                .getSeparateCollectionPoints()
                .doOnSubscribe(d -> dialog.showLoadingIndicator())
                .doAfterTerminate(dialog::hideLoadingIndicator)
                .subscribe(this::onSuccess, this::onError);
    }

    private void onError(Throwable throwable) {

    }

    private void onSuccess(List<SharePoint> sharePoints) {
        BitmapDescriptor descriptor = BitmapDescriptorFactory.fromResource(R.drawable.point_icon);
        Log.e("SUCCESS", "onSuccess:");
        String title;
        Marker marker;
        for (SharePoint sharePoint : sharePoints) {
            if (sharePoint.getInfo() != null) title = sharePoint.getInfo();
            else title = "unknown";
            LatLng sharePointLatLng = new LatLng(sharePoint.getLatitude(), sharePoint.getLongitude());
            MarkerOptions sharePointMarkerOptions = new MarkerOptions()
                    .position(sharePointLatLng)
                    .title(title)
                    .icon(descriptor);
            for (TrashType trashType : sharePoint.getTrashTypes()) {
                switch (trashType.getName()) {
                    case "Бумага":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(false);
                        paperMarkers.add(marker);
                        break;
                    case "Металл":
                        //
                        break;
                    case "Стекло":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        glassMarkers.add(marker);
                        break;
                    case "Пластик":
                        //
                        break;
                }
            }
        }

        CameraPosition cameraPosition = new CameraPosition.Builder().target(paperMarkers.get(0).getPosition()).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(requireContext(), "Permission denied to check your location", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    public void ensurePermissions(Activity activity) {
        if (ContextCompat.checkSelfPermission(
                activity, Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                    activity, permissions, 0
            );
        } else {
            googleMap.setMyLocationEnabled(true);
        }
    }

}