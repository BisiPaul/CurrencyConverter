package com.bisipaul.currencyconverter.components.rates

import android.graphics.Bitmap
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bisipaul.currencyconverter.R
import com.bisipaul.currencyconverter.data.api.models.rates.Rate
import com.bisipaul.currencyconverter.databinding.ItemRateBinding
import com.bisipaul.currencyconverter.utils.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition

/**
 *  Created by paulbisioc on 06.06.2021
 */
class RateViewHolder(
    private val binding: ViewDataBinding,
    private val onCurrencySelected: (currency: String, amount: String, position: Int) -> Unit,
    private val onTextChanged: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private val textWatcher = object : SimpleTextWatcher() {
        override fun onTextChanged(text: String) {
            SharedPreferencesUtils.baseAmount = text
            onTextChanged()
        }
    }

    fun bind(rate: Rate) = with(binding) {
        if (binding !is ItemRateBinding) return

        binding.currencyRateET.removeTextChangedListener(textWatcher)

        val displayValue = getDisplayValue(rate.value)
        val symbol = rate.symbol

        binding.currencySymbolTV.text = symbol
        binding.currencyNameTV.text = CurrencyUtils.getName(root.context, symbol)

        Glide.with(binding.root.context)
            .load(CurrencyUtils.getFlagImageResId(root.context, symbol))
            .placeholder(R.drawable.ic_flag_placeholder)
            .into(binding.flagIV)

        binding.currencyRateET.setText(displayValue)

        if (adapterPosition == 0) {
            binding.currencyRateET.addTextChangedListener(textWatcher)
        }

        // Disable editing for other items than the first one
        binding.currencyRateET.isFocusableInTouchMode = adapterPosition == 0

        binding.root.setOnClickListener {
            onCurrencySelected.invoke(
                symbol,
                displayValue,
                adapterPosition
            )
        }
    }

    private fun getDisplayValue(rate: Double): String {
        var baseAmount: String
        SharedPreferencesUtils.baseAmount.let {
            baseAmount = if (it.isNullOrBlank())
                Constants.DEFAULT_BASE_AMOUNT
            else
                it
        }
        val finalAmount = baseAmount.toDouble() * rate

        // Restrict the decimal places shown to 2
        return String.format("%.2f", finalAmount)
    }
}