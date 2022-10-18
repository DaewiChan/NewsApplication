package com.daewi.newsapplication.common

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Toast

open class NetworkUtilities {
   fun isConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}