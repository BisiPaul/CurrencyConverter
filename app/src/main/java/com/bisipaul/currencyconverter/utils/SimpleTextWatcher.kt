package com.bisipaul.currencyconverter.utils

import android.text.Editable
import android.text.TextWatcher

/**
 *  Created by paulbisioc on 06.06.2021
 */

abstract class SimpleTextWatcher : TextWatcher {
    abstract fun onTextChanged(text: String)

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        onTextChanged(p0.toString())
    }

    override fun afterTextChanged(p0: Editable?) = Unit
    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit
}