package com.bisipaul.currencyconverter.components.main

import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.utils.NetworkChangeReceiver
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    // Used only for simulating
    private var errorSimulated: Boolean = false

    @Inject
    lateinit var networkChangeReceiver: NetworkChangeReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
        registerConnectivityBroadcast()
        // Uncomment this in order to see the ErrorActivity -> MainActivity cycle
//        if(!errorSimulated)
//            simulateError()
    }

    private fun registerConnectivityBroadcast() {
        registerReceiver(
            networkChangeReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    // Used only for simulating
    private fun simulateError() {
        SharedPreferencesUtils.baseCurrency = "err"
        errorSimulated = true
    }
}