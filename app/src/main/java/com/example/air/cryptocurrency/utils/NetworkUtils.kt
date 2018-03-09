package com.example.air.cryptocurrency.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager

object NetworkUtils {

    @SuppressLint("MissingPermission")
    fun networkConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

}