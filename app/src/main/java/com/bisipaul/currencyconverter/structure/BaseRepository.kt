package com.bisipaul.currencyconverter.structure

import com.bisipaul.currencyconverter.core.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

/**
 *  Created by paulbisioc on 05.06.2021
 */

open class BaseRepository {
    @Inject
    lateinit var apiService: ApiService

    suspend fun <T> runApiCall(
        call: suspend () -> Response<T>
    ): RequestResponse<T> = withContext(Dispatchers.IO) {
        try {
            call().createRequestResponse()
        } catch(e: Exception) {
            e.printStackTrace()
            when(e) {
                is IOException -> NoInternetError()
                else -> UnknownError()
            }
        }
    }

    companion object {
        private fun <T> Response<T>.createRequestResponse(): RequestResponse<T> {
            val body = this.body()
            return if (this.isSuccessful && body != null)
                SuccessResponse(body)
            else {
                ErrorResponse()
            }
        }
    }
}