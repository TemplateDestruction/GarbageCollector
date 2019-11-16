package com.example.garbagecollector.view.main

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.example.garbagecollector.R
import com.example.garbagecollector.domain.model.SharePoint
import com.example.garbagecollector.domain.repository.RepositoryProvider
import com.example.garbagecollector.view.standard.LoadingDialog
import com.example.garbagecollector.view.standard.LoadingView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dialog: LoadingView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dialog = LoadingDialog.view(supportFragmentManager)
        button.setOnClickListener {
//            RepositoryProvider
//                    .getJsonRepository()
//                    .separateCollectionPoints
//                    .doOnSubscribe{dialog::showLoadingIndicator}
//                    .doAfterTerminate{dialog::hideLoadingIndicator}
//                    .subscribe { this.onSuccess(it) }
//            startActivity(Intent(this, MapsActivity::class.java))
//            RepositoryProvider
//                    .getJsonRepository()
//                    .separateCollectionPoints
//                    .subscribe {
//                        onSuccess(it)
//                    }
//            RepositoryProvider
//                    .getJsonRepository()
//                    .getSeparateCollectionPointById(1)
//                    .subscribe {
//                       onSuccessId(it)
//                    }
        }
    }

    private fun onSuccessId(sharePoint: SharePoint?) {

    }

    private fun onError() {
        Log.e("getSeparateCollectionPoints", "ERROR")
    }

    private fun onSuccess(sharePoints: MutableList<SharePoint>) {

    }


}
