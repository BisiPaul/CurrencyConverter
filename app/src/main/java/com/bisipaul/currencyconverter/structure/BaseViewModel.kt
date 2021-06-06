package com.bisipaul.currencyconverter.structure

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bisipaul.currencyconverter.utils.SingleLiveEvent
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

/**
 *  Created by paulbisioc on 05.06.2021
 */

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val _showOfflinePage = SingleLiveEvent<Unit>()
    val showOfflinePage: LiveData<Unit> = _showOfflinePage

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}