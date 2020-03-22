package com.example.garbagecollector.utils

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.garbagecollector.R
import com.example.garbagecollector.view.main.BottomNavAct
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.add_alert_dialog_layout.view.*
import kotlinx.android.synthetic.main.basic_alert_dialog_layout.view.*
import kotlinx.android.synthetic.main.basic_alert_dialog_layout.view.title_alert_dialog

class DialogUtils {
    companion object {
        fun showDialog(context: Context, title: String, message: String) {
            val alertadd = AlertDialog.Builder(context)
            val factory = LayoutInflater.from(context)
            val view = factory.inflate(R.layout.basic_alert_dialog_layout, null)
            alertadd.setView(view)
            val dialog = alertadd.create()
            view.title_alert_dialog.text = title
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                view.ok_alert_dialog.text = Html.fromHtml(context.getString(R.string.ok), Html.FROM_HTML_MODE_LEGACY)
            } else {
                view.ok_alert_dialog.text = Html.fromHtml(context.getString(R.string.ok))
            }
            view.ok_alert_dialog.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()
        }

//        fun showAddDialog(context: Context) {
//            val alertadd = AlertDialog.Builder(context)
//            val factory = LayoutInflater.from(context)
//            val view = factory.inflate(R.layout.basic_alert_dialog_layout, null)
//            alertadd.setView(view)
//            val dialog = alertadd.create()
//
//            view.ok_add_dialog.setOnClickListener {
//                (context as BottomNavAct).
//            }
//            view.cancel_alertAdd.setOnClickListener {
//                dialog.dismiss()
//            }
//
//            dialog.show()
//        }

    }

}