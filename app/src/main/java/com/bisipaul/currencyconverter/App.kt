package com.bisipaul.currencyconverter

import android.app.Application
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import dagger.hilt.android.HiltAndroidApp

/**
 *  Created by paulbisioc on 05.06.2021
 */

@HiltAndroidApp
class App: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        initSharedPrefs()
    }

    private fun initSharedPrefs() {
        val sharedPreferences = applicationContext.getSharedPreferences(
            SharedPreferencesUtils.NAME,
            SharedPreferencesUtils.MODE
        )
        SharedPreferencesUtils.init(sharedPreferences)
    }

    companion object {
        private lateinit var instance: App
    }
}