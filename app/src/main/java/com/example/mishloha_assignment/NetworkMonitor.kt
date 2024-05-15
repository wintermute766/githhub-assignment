package com.example.mishloha_assignment

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkRequest

class NetworkMonitor(context: Context) {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun registerNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(), networkCallback)
    }

    fun unregisterNetworkCallback(networkCallback: ConnectivityManager.NetworkCallback) {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}