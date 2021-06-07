package com.bisipaul.currencyconverter.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.bisipaul.currencyconverter.data.api.models.rates.Rates

/**
 *  Created by paulbisioc on 06.06.2021
 */

// Hide keyboard extensions
fun Fragment.hideKeyboard(view: View?) {
    view?.let { activity?.hideKeyboard(it) }
    view?.clearFocus()
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(view.windowToken, 0)
    view.clearFocus()
}