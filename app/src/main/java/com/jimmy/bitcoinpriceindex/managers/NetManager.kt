package com.jimmy.bitcoinpriceindex.managers

import android.content.Context
import android.net.ConnectivityManager

/**
 * checks for connection with internet
 */
class NetManager ( val applicationContext: Context) {

    val isConnectedToInternet: Boolean?
        get() {
            val conManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager

            val ni = conManager.activeNetworkInfo
            return ni != null && ni.isConnected
        }
}