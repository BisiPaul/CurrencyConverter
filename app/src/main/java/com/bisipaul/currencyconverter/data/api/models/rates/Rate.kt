package com.bisipaul.currencyconverter.data.api.models.rates

/**
 *  Created by paulbisioc on 06.06.2021
 */
// Rate is defined as a pair of <abbreviation, value>
class Rate(
    val symbol: String,
    val value: Double
)
