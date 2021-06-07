package com.bisipaul.currencyconverter.components.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bisipaul.currencyconverter.core.ErrorResponse
import com.bisipaul.currencyconverter.core.SuccessResponse
import com.bisipaul.currencyconverter.data.api.models.rates.Rate
import com.bisipaul.currencyconverter.data.api.repositories.rates.RatesRepository
import com.bisipaul.currencyconverter.structure.BaseViewModel
import com.bisipaul.currencyconverter.utils.Constants
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import com.bisipaul.currencyconverter.utils.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class MainViewModel @Inject constructor(
    private val ratesRepository: RatesRepository
) : BaseViewModel() {
    private var _rates = MutableLiveData<List<Rate>>()
    val rates: LiveData<List<Rate>> get() = _rates

    private var _navigateToError = SingleLiveEvent<Unit>()
    val navigateToError: SingleLiveEvent<Unit> get() = _navigateToError

    private var _loadingForFirstTime = SingleLiveEvent<Boolean>()
    val loadingForFirstTime: SingleLiveEvent<Boolean> get() = _loadingForFirstTime

    private var fetchRatesJob: Job? = null

    private fun getRatesInternal() {
        viewModelScope.launch {
            val isFirstTimeLoading = rates.value.isNullOrEmpty()
            if(isFirstTimeLoading)
                _loadingForFirstTime.value = true
            when (val response = ratesRepository.getRates(
                SharedPreferencesUtils.baseCurrency ?: Constants.DEFAULT_BASE_CURRENCY
            )) {
                is SuccessResponse -> {
                    response.data?.let {
                        _rates.value = it.rates.toList()
                    }
                    _loadingForFirstTime.value = false
                }
                is ErrorResponse -> {
                    stopFetchingRates()
                    _rates.value = listOf()
                    _loadingForFirstTime.value = false
                    _navigateToError.value = Unit
                }
            }
        }
    }

    fun getRates() {
        fetchRatesJob = viewModelScope.launch {
            while (isActive) {
                getRatesInternal()
                delay(1000)
            }
        }
    }

    fun setNewBaseValues(newCurrency: String, newAmount: String) {
        SharedPreferencesUtils.baseCurrency = newCurrency
        SharedPreferencesUtils.baseAmount = newAmount
    }

    fun stopFetchingRates() {
        fetchRatesJob?.cancel()
    }
}