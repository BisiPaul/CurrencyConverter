package com.bisipaul.currencyconverter.data.api.repositories.rates

import com.bisipaul.currencyconverter.core.RequestResponse
import com.bisipaul.currencyconverter.data.api.models.apiResponse.GetRatesResponse
import com.bisipaul.currencyconverter.structure.BaseRepository
import javax.inject.Inject

/**
 *  Created by paulbisioc on 05.06.2021
 */
class RatesRepository @Inject constructor(): BaseRepository() {
    suspend fun getRates(baseCurrency: String): RequestResponse<GetRatesResponse?> =
        runApiCall {
            apiService.getRates(baseCurrency)
        }
}