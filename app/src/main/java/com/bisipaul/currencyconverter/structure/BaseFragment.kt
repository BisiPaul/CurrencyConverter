package com.bisipaul.currencyconverter.structure

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 *  Created by paulbisioc on 06.06.2021
 */
abstract class BaseFragment : Fragment() {

    fun observe(baseViewModel: BaseViewModel) = with(baseViewModel) {
    }

    fun <T> LiveData<T>.observe(observer: Observer<T>) {
        this.observe(this@BaseFragment.viewLifecycleOwner, observer)
    }
}