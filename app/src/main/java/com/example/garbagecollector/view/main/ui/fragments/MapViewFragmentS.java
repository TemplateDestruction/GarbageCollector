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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MapViewFragmentS extends Fragment
//        implements GoogleMap.OnMarkerClickListener
{
    boolean firstClick = true;
    LoadingView dialog;
    List<SharePoint> sharePoints;
    MapView mMapView;
    private GoogleMap googleMap;
    FloatingActionButton myLocationBtn;
    CheckBox glassFilter, paperFilter, plasticFilter, metalFilter;
    ConstraintLayout glassTrashBtn, paperTrashBtn, plasticTrashBtn, metalTrashBtn;
    String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION};
    ArrayList<Marker> paperMarkers = new ArrayList<>();
    ArrayList<Marker> glassMarkers = new ArrayList<>();
    ArrayList<Marker> plasticMarkers = new ArrayList<>();
    ArrayList<Marker> metalMarkers = new ArrayList<>();
    RelativeLayout mainLay;
    private ImageView locationButton;

    private LinearLayout bottomSheet;
    private TextView pointName, pointStreet, pointTrashTypes,
            pointOperationMode, trashPointState, directionToTrashPoint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dialog = LoadingDialog.view(getFragmentManager());
        View rootView = inflater.inflate(R.layout.container_view, container, false);
        mMapView = (MapView) rootView.findViewById(R.id.mapViewNew);
        mMapView.onCreate(savedInstanceState);


        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        initMap();
        return rootView;
    }


    private void initMap() {
        Log.e("Called map async", "initMap: ");
        mMapView.getMapAsync(mMap -> {
            googleMap = mMap;
//            LatLng sydney = new LatLng(-34, 151);
//            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//            // For zooming automatically to the location of the marker
//            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            locationButton = ((View) mMapView.findViewById(1).getParent()).findViewById(2);
            // Change the visibility of my location button
//            locationButton.setVisibility(View.GONE);
            googleMap.getUiSettings().setCompassEnabled(false);
            getTrashCollectionPoints();
            ensurePermissions(requireActivity());
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        bottomSheet = view.findViewById(R.id.bottom_sheet_reference);
        myLocationBtn = view.findViewById(R.id.myLocationButton);
        mainLay = view.findViewById(R.id.main_lay_reference);
        glassFilter = mainLay.findViewById(R.id.glass_filter_btn);
        glassTrashBtn = mainLay.findViewById(R.id.glass_trash_btn);
        paperFilter = mainLay.findViewById(R.id.paper_filter_btn);
        paperTrashBtn = mainLay.findViewById(R.id.paper_trash_btn);
        plasticFilter = mainLay.findViewById(R.id.plastic_filter_btn);
        plasticTrashBtn = mainLay.findViewById(R.id.plastic_trash_btn);
        metalFilter = mainLay.findViewById(R.id.metal_filter_btn);
        metalTrashBtn = mainLay.findViewById(R.id.metal_trash_btn);

//        pointName = mainLay.findViewById(R.id.pointName_bottom_sheet);
//        pointStreet = mainLay.findViewById(R.id.pointStreet_bottom_sheet);
//        pointTrashTypes = mainLay.findViewById(R.id.pointTrashTypes_bottom_sheet);
//        pointOperationMode = mainLay.findViewById(R.id.pointOperationMode_bottom_sheet);
//        trashPointState = mainLay.findViewById(R.id.trashPointState_bottom_sheet);
//        directionToTrashPoint = mainLay.findViewById(R.id.directionToTrashPointBtn_bottom_sheet);

        glassTrashBtn.setOnClickListener(view1 -> glassFilter.performClick());
        paperTrashBtn.setOnClickListener(view1 -> paperFilter.performClick());
        plasticTrashBtn.setOnClickListener(view1 -> plasticFilter.performClick());
        metalTrashBtn.setOnClickListener(view1 -> metalFilter.performClick());

        myLocationBtn.setOnClickListener(view12 -> {
            if (!googleMap.isMyLocationEnabled()) {
                ensurePermissions(requireActivity());
                Toast.makeText(requireContext(), "Обновление карты", Toast.LENGTH_SHORT).show();
            } else locationButton.performClick(); });


        glassFilter.setOnCheckedChangeListener((glassFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                glassMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                glassMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        paperFilter.setOnCheckedChangeListener((paperFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                paperMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                paperMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        plasticFilter.setOnCheckedChangeListener((plasticFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    metalMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                plasticMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                plasticMarkers.forEach(marker -> marker.setVisible(false));
            }
        });
        metalFilter.setOnCheckedChangeListener((metalFilter, checked) -> {
            if (checked) {
                if (firstClick) {
                    plasticMarkers.forEach(marker -> marker.setVisible(false));
                    glassMarkers.forEach(marker -> marker.setVisible(false));
                    paperMarkers.forEach(marker -> marker.setVisible(false));
                    firstClick = false;
                }
                metalMarkers.forEach(marker -> marker.setVisible(true));
            } else {
                metalMarkers.forEach(marker -> marker.setVisible(false));
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
        throwable.fillInStackTrace();
    }
    private void onSuccess(List<SharePoint> sharePoints) {
        this.sharePoints = sharePoints;
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
            Log.e("S u c c ", "onSuccess:");
            if (sharePoint.getTrashTypes() != null) {
            for (TrashType trashType : sharePoint.getTrashTypes()) {
                if (trashType.getName() != null) {
                    System.out.println("TRASH TYPE: " + trashType.getName());
                switch (trashType.getName()) {
                    case "Бумага":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        paperMarkers.add(marker);
                        break;
                    case "Металл":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        metalMarkers.add(marker);
                        break;
                    case "Стекло":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        glassMarkers.add(marker);
                        break;
                    case "Пластик":
                        marker = googleMap.addMarker(sharePointMarkerOptions);
                        marker.setVisible(true);
                        plasticMarkers.add(marker);
                        break;
                }
            }}
        }}
        Log.e("S u c c e s s", "onSuccess:");
        ensurePermissions(requireActivity());
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(55.798551, 49.106324)).zoom(3).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                locationButton.setVisibility(View.GONE);
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
            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            locationButton.setVisibility(View.GONE);
        }
    }

//    @Override
//    public boolean onMarkerClick(Marker marker) {
//        for (SharePoint sharePoint : sharePoints) {
//            if (sharePoint.getInfo().equals(marker.getTitle())) {
//                pointName.setText(sharePoint.getInfo());
//                pointStreet.setText(sharePoint.getAddress());
//                StringBuilder stringBuilder = new StringBuilder();
//                sharePoint.getTrashTypes().forEach(stringBuilder::append);
//                pointTrashTypes.setText(String.join(", ", stringBuilder));
//                pointOperationMode.setText(sharePoint.getOpenHours());
//                if (!sharePoint.getFull()) {
//                    trashPointState.setText("Свободен");
//                    trashPointState.setBackgroundColor(requireContext().getColor(R.color.dark_green));
//                }
//                // TODO: 9/29/2019 маршрут
////                directionToTrashPoint
//
//            }
//        }
//        return false;
//    }
}