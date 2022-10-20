package ru.denis.convertertorub.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import dagger.hilt.android.qualifiers.ApplicationContext
import ru.denis.convertertorub.R
import javax.inject.Inject

class NetWorkUtils @Inject constructor(@ApplicationContext private val context: Context) {

    private fun checkTypeOfTransport(capabilities: NetworkCapabilities, typeConnection: Int): Boolean {
        return if (capabilities.hasTransport(typeConnection)) {
            Log.e(context.getString(R.string.Internet), typeConnection.toString())
            true
        } else {
            false
        }
    }

    fun hasNetworkConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        return connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)?.let {
                capabilities ->
            when {
                checkTypeOfTransport(capabilities, NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                checkTypeOfTransport(capabilities, NetworkCapabilities.TRANSPORT_WIFI) -> true
                checkTypeOfTransport(capabilities, NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } ?: false
    }
}