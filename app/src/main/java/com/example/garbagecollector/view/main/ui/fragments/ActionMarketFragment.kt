package com.example.garbagecollector.view.main.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.garbagecollector.R
import kotlinx.android.synthetic.main.action_market_fragment.*

class ActionMarketFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.action_market_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nearestShop_actionMarketFrag.setOnClickListener {
            findNavController().navigate(R.id.navigation_map_frag, Bundle().apply {putString("action", "shop")})
//            val gmmIntentUri = Uri.parse("google.navigation:q=" + 55.7305897 + "," + 37.6238789)
//            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//            mapIntent.setPackage("com.google.android.apps.maps")
//            startActivity(mapIntent)
        }
    }
}