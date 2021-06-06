package com.bisipaul.currencyconverter.components.noInternet

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.databinding.NoInternetActivityBinding
import com.bisipaul.currencyconverter.utils.NetworkChangeReceiver
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by paulbisioc on 07.06.2021
 */
@AndroidEntryPoint
class NoInternetActivity: AppCompatActivity() {
    private lateinit var binding: NoInternetActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.no_internet_activity)
        setControls()
    }

    private fun setControls() {
        binding.retryBT.setOnClickListener {
            if (!NetworkChangeReceiver.isNetworkConnected(applicationContext)) {
                Toast.makeText(this, resources.getString(R.string.warning_connect_to_internet), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                finish()
            }
        }
    }
}