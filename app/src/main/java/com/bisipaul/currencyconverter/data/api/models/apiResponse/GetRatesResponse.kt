package com.bisipaul.currencyconverter.data.api.models.apiResponse

import com.bisipaul.currencyconverter.data.api.models.rates.Rates
import com.squareup.moshi.JsonClass

/**
 *  Created by paulbisioc on 05.06.2021
 */

@JsonClass(generateAdapter = true)
data class GetRatesResponse(
    val baseCurrency: String,
    val rates: Rates
)