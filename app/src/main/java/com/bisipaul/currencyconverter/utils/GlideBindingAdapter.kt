package com.bisipaul.currencyconverter.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bisipaul.currencyconverter.R
import com.bumptech.glide.Glide

/**
 *  Created by paulbisioc on 07.06.2021
 */

@BindingAdapter("flagImg")
fun bindImage(iv: ImageView, drawable: Int?) {
    Glide.with(iv.context)
        .load(drawable)
        .placeholder(R.drawable.ic_flag_placeholder)
        .into(iv)
}