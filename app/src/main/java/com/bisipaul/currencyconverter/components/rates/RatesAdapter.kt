package com.bisipaul.currencyconverter.components.rates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.data.api.models.rates.Rate
import com.bisipaul.currencyconverter.utils.Constants
import com.bisipaul.currencyconverter.utils.SharedPreferencesUtils
import java.util.*

/**
 *  Created by paulbisioc on 06.06.2021
 */
class RatesAdapter(
    private val callback: RatesAdapterCallback
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var rates: List<Rate> = listOf()
    private var shouldRedrawFirstElement: Boolean = false

    private var onCurrencySelected: (selectedCurrency: String, selectedAmount: String, position: Int) -> Unit = { currency, amount, pos ->
        callback.onCurrencySelected(currency, amount)
        Collections.swap(rates, pos, 0)
        notifyItemMoved(pos, 0)
        shouldRedrawFirstElement = true
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_rate, parent, false
            ), onCurrencySelected
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RateViewHolder).bind(rates[position])
    }

    override fun getItemCount(): Int = rates.size

    fun submitNewList(newList: List<Rate>) {
        val baseCurrency = SharedPreferencesUtils.baseCurrency ?: Constants.DEFAULT_BASE_CURRENCY
        val baseRate = Constants.BASE_RATE.toDouble()

        val firstItem = Rate(baseCurrency, baseRate)
        rates = listOf(firstItem) + newList

        if(shouldRedrawFirstElement) {
            notifyDataSetChanged()
            shouldRedrawFirstElement = false
        } else {
            //notifyItemRangeChanged(1, rates.size-1)
            notifyItemRangeChanged(1, rates.size-1, Constants.PAYLOAD_AMOUNT)
        }
    }
}