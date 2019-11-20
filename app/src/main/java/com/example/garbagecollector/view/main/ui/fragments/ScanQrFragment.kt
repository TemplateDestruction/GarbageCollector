package xyz.tusion.vtb_hackathon.presentation

import android.content.Intent
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
import com.example.garbagecollector.utils.DialogUtils
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentIntegrator.forSupportFragment


class ScanQrFragment : Fragment() {
    companion object {
        const val SCAN_QR_CONTENT_CODE = "SCAN_QR_CONTENT_CODE"
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        createScanIntent()
//        return inflater.inflate(R.layout.main_act, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        createScanIntent()
    }

    private fun createScanIntent() {
        forSupportFragment(this as Fragment)
                .setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
                .setPrompt(getString(R.string.barcode_title))
                .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            RepositoryProvider
                    .getJsonRepository()
                    .getGoodInfo(result.contents, "55.798551", "49.106324")
                    .subscribe({ onSuccess(it) }, { this.onError(it) })

//            findNavController().navigate(
//                    R.id.action_navigation_barcode_to_checkDetailsFragment,
//                    Bundle().apply {
//                        putString(SCAN_QR_CONTENT_CODE, result.contents)
//                    }
//            )
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun onError(t: Throwable) {
//        createScanIntent()
        findNavController().popBackStack()
        DialogUtils.showDialog(requireContext(), "товар с таким QR кодом не найден", "")
        Log.e("onERROR", t.localizedMessage)
    }

    private fun onSuccess(goodInfo: GoodInfo) {
        findNavController().navigate(
                R.id.action_navigation_barcode_to_checkDetailsFragment,
                Bundle().apply {
                    putSerializable(SCAN_QR_CONTENT_CODE, goodInfo)
                })
    }


}
