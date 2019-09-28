package com.example.garbagecollector.view.main

import androidx.fragment.app.FragmentActivity

import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.example.garbagecollector.R
import com.example.garbagecollector.domain.model.SharePoint
import com.example.garbagecollector.domain.repository.RepositoryProvider
import com.example.garbagecollector.view.standard.LoadingDialog
import com.example.garbagecollector.view.standard.LoadingView
import com.google.android.gms.maps.model.*


class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    lateinit var dialog: LoadingView
    private lateinit var mMap: GoogleMap
    val places = mutableListOf<LatLng>()
    override fun onCreate(savedInstanceState: Bundle?) {
        dialog = LoadingDialog.view(supportFragmentManager)
        RepositoryProvider
                .getJsonRepository()
                .separateCollectionPoints
                .doOnSubscribe {dialog.showLoadingIndicator()}
                .doAfterTerminate { dialog.hideLoadingIndicator()}
                .subscribe {
                    onSuccess(it)
                }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.


//        MarkerOptions().icon()
    }

    private fun onSuccess(sharePoints: MutableList<SharePoint>) {
        for (sharePoint in sharePoints) {
//            latlng = LatLng(sharePoint.getLat, sharePoint.getLng)
//            places.add(latlng)
//            mMap.addMarker(MarkerOptions()
//                    .position(latlng)
//                    .title(sharePoint.title)
//            )
        }

    }
}
