package com.bisipaul.currencyconverter.utils

import android.content.Context
import android.content.SharedPreferences

/**
 *  Created by paulbisioc on 06.06.2021
 */

object SharedPreferencesUtils {
    const val MODE = Context.MODE_PRIVATE
    const val NAME = "currency_converter_shared_preferences"

    private const val BASE_CURRENCY = "base_currency"
    private const val BASE_AMOUNT = "base_amount"

    private lateinit var preferences: SharedPreferences

    fun init(preferences: SharedPreferences) {
        this.preferences = preferences
    }

    private inline fun edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = preferences.edit()
        operation(editor)
        editor.apply()
    }

    var baseCurrency: String?
        get() = preferences.getString(BASE_CURRENCY, null)
        set(value) = edit { it.putString(BASE_CURRENCY, value) }

    var baseAmount: String?
        get() = preferences.getString(BASE_AMOUNT, Constants.DEFAULT_BASE_AMOUNT)
        set(value) = edit { it.putString(BASE_AMOUNT, value) }

    fun reinitialize() {
        baseCurrency = Constants.DEFAULT_BASE_CURRENCY
        baseAmount = Constants.DEFAULT_BASE_AMOUNT
    }
}