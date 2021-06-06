package com.bisipaul.currencyconverter.components.rates

/**
 *  Created by paulbisioc on 06.06.2021
 */

interface RatesAdapterCallback {
    fun onCurrencySelected(newCurrency: String, newAmount: String)
}