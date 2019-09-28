package xyz.tusion.vtb_hackathon.presentation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.garbagecollector.R
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentIntegrator.forSupportFragment
import kotlinx.android.synthetic.main.alert_scan_or_manual.*
import kotlinx.android.synthetic.main.alert_scan_or_manual.view.*
import kotlinx.android.synthetic.main.prepare_to_scan_lay.*


class PrepareToScanFragment : Fragment() {
    companion object {
        const val SCAN_QR_CONTENT_CODE = "SCAN_QR_CONTENT_CODE"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.prepare_to_scan_lay, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        showNewAppDialog(requireContext())
        prepare_to_scan_btn.setOnClickListener {
            toScanner()
        }
        manual_scan_barcode.setOnClickListener {
            it.barcode_form.visibility = View.VISIBLE
        }
    }

    private fun toScanner() {
        forSupportFragment(this as Fragment)
                .setDesiredBarcodeFormats(IntentIntegrator.EAN_13)
                .setPrompt(getString(R.string.barcode_title))
                .initiateScan()
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            findNavController().navigate(
                    R.id.action_navigation_barcode_to_checkDetailsFragment,
                    Bundle().apply {
                        putString(SCAN_QR_CONTENT_CODE, result.contents)
                    }
            )
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    fun showNewAppDialog(context: Context) {
        val alertadd = AlertDialog.Builder(context)
        val factory = LayoutInflater.from(context)
        val view = factory.inflate(R.layout.alert_scan_or_manual, null)
        alertadd.setView(view)
        val dialog = alertadd.create()
        view.alert_scan_ok.setOnClickListener {
            toScanner()
        }
        view.alert_manual_ok.setOnClickListener {
//            view.alert_scan_ok.visibility = View.INVISIBLE
            view.alert_manual_ok.visibility = View.INVISIBLE
            view.barcode_form.visibility = View.VISIBLE
//            view.txt_pin_entry.visibility = View.VISIBLE

        }
//        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.show()
    }
}
