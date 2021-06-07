package com.bisipaul.currencyconverter.components.error

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.databinding.ErrorActivityBinding
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import dagger.hilt.android.AndroidEntryPoint

/**
 *  Created by paulbisioc on 07.06.2021
 */
@AndroidEntryPoint
class ErrorActivity : AppCompatActivity() {
    private lateinit var binding: ErrorActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.error_activity)
        setControls()
    }

    private fun setControls() {
        binding.reinitializeBT.setOnClickListener {
            SharedPreferencesUtils.reinitialize()
            finish()
        }
    }
}