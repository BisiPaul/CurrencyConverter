package com.bisipaul.currencyconverter.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.PowerManager
import com.bisipaul.currencyconverter.components.noInternet.NoInternetActivity
import javax.inject.Inject

/**
 *  Created by paulbisioc on 07.06.2021
 */
class NetworkChangeReceiver @Inject constructor() : BroadcastReceiver() {

    companion object {
        fun isNetworkConnected(context: Context?): Boolean {
            if (isDozing(context)) {
                return false
            }
            val connectivityManager =
                context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                connectivityManager?.run {
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                        ?.run {
                            return when {
                                hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                                hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                                else -> false
                            }
                        }
                }
            } else {
                @Suppress("DEPRECATION")
                val nwInfo = connectivityManager?.activeNetworkInfo ?: return false
                @Suppress("DEPRECATION")
                return nwInfo.isConnected
            }
            return false
        }

        private fun isDozing(context: Context?): Boolean {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val powerManager = context?.getSystemService(Context.POWER_SERVICE) as? PowerManager
                powerManager?.isDeviceIdleMode == true
            } else {
                false
            }
        }
    }

    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.action?.let { action ->
            if (action == ConnectivityManager.CONNECTIVITY_ACTION) {
                if (!isNetworkConnected(context)) {
                    val intentToNoInternet = Intent()
                    intentToNoInternet.setClassName(
                        context!!.packageName,
                        NoInternetActivity::class.java.name
                    )
                    intentToNoInternet.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intentToNoInternet)
                }
            }
        }
    }
}