package app.jontromanob.com.pihr_kotlin.util

import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat
import android.support.v4.content.LocalBroadcastManager
import android.view.View
import android.widget.TextView
import app.jontromanob.com.pihr_kotlin.R

/**
 * Created by Hi on 3/25/2019.
 */
class CustomSnackbar {


    companion object {

        val SNACKBAR_INTENT = "snackbar-intent"

        fun showMessageFromFragment(context: Context, message: String) {
            val intent = Intent(SNACKBAR_INTENT)
            intent.putExtra("message", message)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }

        fun showMessage(context: Context,view: View,message: String){
            val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
            val snackbarView = snackbar.view
            snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
            val textView = snackbarView.findViewById(android.support.design.R.id.snackbar_text) as TextView
            textView.maxLines = 5
            snackbar.show()
        }
    }


}