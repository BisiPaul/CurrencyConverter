package com.bisipaul.currencyconverter.data.api.repositories.rates

import com.bisipaul.currencyconverter.core.ErrorResponse
import com.bisipaul.currencyconverter.core.SuccessResponse
import com.bisipaul.currencyconverter.data.api.models.apiResponse.GetRatesResponse
import com.bisipaul.currencyconverter.data.api.models.rates.Rates
import io.mockk.*
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.random.Random


/**
 * Created by paulbisioc on 07.06.2021
 */
class MainViewModelTest {

    private var repository: RatesRepository = mockk(relaxed = true)

    @Before
    fun setup() {
    }

    private fun setupSuccessRepository() = runBlocking {
        val randomDoubles = List(31) { Random.nextDouble(0.0, 10.0)}
        coEvery { repository.getRates("EUR") } returns SuccessResponse(GetRatesResponse("EUR", Rates(randomDoubles)))
    }

    private fun setupErrorRepository() = runBlocking {
        coEvery { repository.getRates("err") } returns ErrorResponse()
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `test list of rates has always size of 31`() {
        setupSuccessRepository()

        runBlocking {
            when (val response = repository.getRates("EUR")) {
                is SuccessResponse -> {
                    assertEquals(31, response.data?.rates?.toList()?.size)
                }
            }
        }
    }

    @Test
    fun `test list of rates with wrong baseCurrency returns ErrorResponse`() {
        setupErrorRepository()
        var flag = false

        runBlocking {
            when (val response = repository.getRates("err")) {
                is ErrorResponse -> {
                    flag = true
                }
            }
            assert(flag)
        }
    }
}