package com.bisipaul.currencyconverter.components.rates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
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
    private val differ: AsyncListDiffer<Rate> = AsyncListDiffer(this, DiffCallback())

    private var onCurrencySelected: (selectedCurrency: String, selectedAmount: String, position: Int) -> Unit = { currency, amount, pos ->
        callback.onCurrencySelected(currency, amount)
        val newList = currentList().toMutableList()
        Collections.swap(newList, pos, 0)
        submitList(newList)
    }

    private var onTextChanged: () -> Unit = {
        submitList(currentList().toList())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        RateViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context), R.layout.item_rate, parent, false
            ), onCurrencySelected, onTextChanged
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as RateViewHolder).bind(differ.currentList[position])
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        val item = currentList()[position]

        if (payloads.isEmpty() || payloads[0] !is Bundle) {
            (holder as RateViewHolder).bind(item)
        } else {
            val bundle = payloads[0] as Bundle
            (holder as RateViewHolder).update(bundle)
        }
    }

    override fun getItemCount(): Int = currentList().size

    fun submitNewList(newList: List<Rate>) {
        val baseCurrency = SharedPreferencesUtils.baseCurrency ?: Constants.DEFAULT_BASE_CURRENCY
        val baseRate = Constants.BASE_RATE.toDouble()

        val firstItem = Rate(baseCurrency, baseRate)
        val newList = listOf(firstItem) + newList

        submitList(newList)
    }

    private class DiffCallback : DiffUtil.ItemCallback<Rate>() {

        override fun areItemsTheSame(oldItem: Rate, newItem: Rate) =
            oldItem.symbol == newItem.symbol

        override fun areContentsTheSame(oldItem: Rate, newItem: Rate) =
            oldItem == newItem

        // This gets called when areItemsTheSame == true && areContentsTheSame == false
        override fun getChangePayload(oldItem: Rate, newItem: Rate): Any? {
            if(oldItem.symbol == newItem.symbol) {
                return if(oldItem.value == newItem.value)
                    super.getChangePayload(oldItem, newItem)
                else {
                    val diff = Bundle()
                    diff.putDouble(Constants.PAYLOAD_AMOUNT, newItem.value)
                    diff
                }
            }

            return super.getChangePayload(oldItem, newItem)
        }
    }

    fun submitList(list: List<Rate>) {
        differ.submitList(list)
    }

    fun currentList(): List<Rate> {
        return differ.currentList
    }

}