package com.example.garbagecollector.view.main.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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
        val goodInfo = arguments?.getSerializable(SCAN_QR_CONTENT_CODE) as GoodInfo
        if (goodInfo == null) {
            findNavController().navigate(R.id.navigation_map_frag)
        } else {
            onSuccess(goodInfo)
//            RepositoryProvider
//                    .getJsonRepository()
//                    .getGoodInfo(barCode, "55.798551", "49.106324")
//                    .subscribe({ onSuccess(it) }, { this.onError(it) })
        }
    }

    private fun onError(t: Throwable) {
        Log.e("onERROR", t.localizedMessage)
        t.printStackTrace()
    }

    private fun onSuccess(goodInfo: GoodInfo) {
        Log.e("trashType:", goodInfo.trashType.name)
        Log.e("Preparation: ", goodInfo.trashType.preparation)
        toPoint.setOnClickListener {
            findNavController().navigate(
                    R.id.navigation_map_frag,
                    Bundle().apply {
//                        putString("id", goodInfo.nearestPoint.id.toString())
                        putString("id", "42")
                        putString("marker", "tandem")
                        putString("name", goodInfo.nearestPoint.info)
                        putString("street", goodInfo.nearestPoint.address)
//                        putString("name", "Контейнер Эколайн")
//                        putString("street", "Покровка, 12")
                        putBoolean("state", goodInfo.nearestPoint.full)
                        putString("mode", goodInfo.nearestPoint.openHours)
                        putString("distance", goodInfo.nearestPoint.distance)
                    })
        }
        randomArticleBtn.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(goodInfo.article.link)))
        }
        showOnMap_fragment_details.setOnClickListener {
                        findNavController().navigate(R.id.navigation_map_frag)
//            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(goodInfo.article.link)))
        }
        marketAction_checkDetailsFragment.setOnClickListener {
            findNavController().navigate(R.id.actionMarketFragment)
        }
        banner_checkDetailsFrag.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.perekrestok.ru/catalog/soki-vody-napitki/vody-mineralnye-pitevye/voda-bonaqua-negazir-pet-0-5l--304230")))
        }
        Log.e("onSuccess", "entered")
        Picasso.get().load(goodInfo.image).fit().into(good_img_fragment_details)
        materialType_fragment_details.text = goodInfo.trashType.name
        goodName_fragment_details.text = goodInfo.name
        description_fragment_details.text = goodInfo.trashType.preparation
//        articleTitle_fragment_details.text = goodInfo.article.title
//        article_fragment_details.text = goodInfo.article.text.substring(0, 10) + "..."
//        containterName_fragment_details.text = goodInfo.nearestPoint.info
//        containerStreet_fragment_details.text = goodInfo.nearestPoint.address
        containterName_fragment_details.text = "КОНТЕЙНЕР \"ДОБРОВОРОТ\" "
        containerStreet_fragment_details.text = "2-й Кожуховский пр-д, дом. 12, стр. 1"
        var stringBuilder = StringBuilder()
        var i = goodInfo.nearestPoint.trashTypes.size
        for (trashType in goodInfo.nearestPoint.trashTypes) {
            if (i != 1) {
                stringBuilder.append(trashType.name).append(", ")
            } else stringBuilder.append(trashType.name)
            i -= 1
        }
        trashTypes_fragment_details.text = stringBuilder.toString()


    }
}