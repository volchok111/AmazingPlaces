package com.example.amazingplaces.ui.home

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatFragment
import com.example.amazingplaces.R

open class HomeFragment: MvpAppCompatFragment(), HomeView {

    /**
     * This function is called when the request is started.
     */
    override fun onRequestStart() {}

    /**
     * An override function that does nothing.
     */
    override fun onRequestComplete() {}

    /**
     * It shows a toast message with the error message.
     *
     * @param message The message to be displayed in the Toast.
     */
    override fun onRequestError(message: Int) {
        Toast.makeText(context, getString(message), Toast.LENGTH_LONG).show()
    }

    /**
     * It shows a dialog with a message and a button.
     */
    override fun onConnectionAbsence() {
        showSettingsMessage(
            requireContext(), { dialog, which ->
                dialog.dismiss()
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            },
            getString(R.string.no_internet_connection),
            getString(R.string.check_network_settings)
        )
    }

    /**
     * It shows a dialog to the user with the message and title passed in the parameters.
     *
     * @param context Context
     * @param positiveListener This is the listener that will be called when the user clicks on the
     * positive button.
     * @param permissions The list of permissions to be requested.
     */
    private fun showSettingsMessage(
        context: Context,
        positiveListener: DialogInterface.OnClickListener?,
        vararg permissions: String?
    ) {
        val alertDialog: AlertDialog = AlertDialog.Builder(activity)
            .setCancelable(true)
            .setTitle(permissions[0])
            .setMessage(permissions[1])
            .setPositiveButton(R.string.open, positiveListener)
            .setNegativeButton(
                R.string.action_cancel
            ) { dialog, which -> activity?.finish() }
            .create()
        alertDialog.setCanceledOnTouchOutside(false)
        if (!activity?.isDestroyed!!) alertDialog.show()
    }

    /**
     * It opens the Google Maps app with the given latitude and longitude.
     *
     * @param lat latitude of the location
     * @param lon Longitude
     */
    open fun onShowMap(lat: String, lon: String) {
        val gmmIntentUri =
            Uri.parse("geo:0,0?q=${lat},${lon}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
    }
}