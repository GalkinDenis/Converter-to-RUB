package ru.denis.convertertorub.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import ru.denis.convertertorub.R
import javax.inject.Inject

class CheckNetWork @Inject constructor(
    private val context: Context
) {

    fun checkOnline(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)

        if (capabilities != null) {
            when {
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) -> {
                    Log.i(
                        context.getString(R.string.Internet),
                        context.getString(R.string.TRANSPORT_CELLULAR)
                    )
                    return true
                }
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) -> {
                    Log.i(
                        context.getString(R.string.Internet),
                        context.getString(R.string.TRANSPORT_WIFI)
                    )
                    return true
                }
                (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) -> {
                    Log.i(
                        context.getString(R.string.Internet),
                        context.getString(R.string.TRANSPORT_ETHERNET)
                    )
                    return true
                }
            }
        }
        return false
    }
}