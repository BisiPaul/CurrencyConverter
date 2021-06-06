package com.bisipaul.currencyconverter.components.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}