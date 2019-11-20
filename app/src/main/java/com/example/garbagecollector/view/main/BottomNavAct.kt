package com.example.garbagecollector.view.main

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.garbagecollector.R

class BottomNavAct : AppCompatActivity() {

    private var permissions = arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_bottom_nav)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_barcode, R.id.navigation_map_frag, R.id.navigation_news))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
//        ensurePermissions()


    }

    fun ensurePermissions() {
        if (ContextCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, permissions, 0
            )
        } else {
            Toast.makeText(this, "Permission granted to check your location", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.e("MapViewFrag", "onRequestPermissionsResult")
        if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //            PreferenceUtils.saveLocationPermitted();
            //            findLocation();
            Log.e("MapViewFrag", "onRequestPermissionsResult: TRUE")
            Toast.makeText(this, "Разрешение на опредление геопозиции предоставлено", Toast.LENGTH_SHORT).show()
        } else {
            Log.e("MapViewFrag", "onRequestPermissionsResult: FALSE")
            ActivityCompat.requestPermissions(
                    this, permissions, 0
            )//            Toast.makeText(requireContext(), "Разрешение на опредление геопозиции не предоставлено", Toast.LENGTH_SHORT).show();
        }
    }
}
