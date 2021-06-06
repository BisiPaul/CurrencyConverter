package com.bisipaul.currencyconverter.core

import com.bisipaul.currencyconverter.data.api.models.apiResponse.GetRatesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *  Created by paulbisioc on 05.06.2021
 */

interface ApiService {
    @GET("latest")
    suspend fun getRates(
        @Query("base") currency: String
    ): Response<GetRatesResponse?>
}