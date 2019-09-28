package com.example.garbagecollector.view.main.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.garbagecollector.R
import com.example.garbagecollector.domain.model.good.GoodInfo
import com.example.garbagecollector.domain.repository.RepositoryProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_check_details.*
import xyz.tusion.vtb_hackathon.presentation.ScanQrFragment.Companion.SCAN_QR_CONTENT_CODE

class CheckDetailsFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_check_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Toast.makeText(requireContext(), arguments?.getString(SCAN_QR_CONTENT_CODE), Toast.LENGTH_SHORT).show()
        val barCode = arguments?.getString(SCAN_QR_CONTENT_CODE)
        RepositoryProvider
                .getJsonRepository()
                .getGoodInfo(barCode)
                .subscribe {onSuccess(it)}
    }

    private fun onSuccess(goodInfo: GoodInfo) {
        Picasso.get().load(goodInfo.image).fit().into(good_img)
        good_description.text = goodInfo.markings[0].description;
    }
}