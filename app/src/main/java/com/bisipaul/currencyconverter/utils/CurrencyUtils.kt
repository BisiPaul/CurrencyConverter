package com.bisipaul.currencyconverter.utils

import android.content.Context
import com.bisipaul.currencyconverter.R

/**
 *  Created by paulbisioc on 05.06.2021
 */

object CurrencyUtils {
    fun getFlagImageResId(context: Context, code: String): Int {
        when (code) {
            context.getString(R.string.aud) -> return R.drawable.ic_australia
            context.getString(R.string.bgn) -> return R.drawable.ic_bulgaria
            context.getString(R.string.brl) -> return R.drawable.ic_brazil
            context.getString(R.string.cad) -> return R.drawable.ic_canada
            context.getString(R.string.chf) -> return R.drawable.ic_switzerland
            context.getString(R.string.cny) -> return R.drawable.ic_china
            context.getString(R.string.czk) -> return R.drawable.ic_czech_republic
            context.getString(R.string.dkk) -> return R.drawable.ic_denmark
            context.getString(R.string.eur) -> return R.drawable.ic_european_union
            context.getString(R.string.gbp) -> return R.drawable.ic_united_kingdom
            context.getString(R.string.hkd) -> return R.drawable.ic_hong_kong
            context.getString(R.string.hrk) -> return R.drawable.ic_croatia
            context.getString(R.string.huf) -> return R.drawable.ic_hungary
            context.getString(R.string.idr) -> return R.drawable.ic_indonesia
            context.getString(R.string.ils) -> return R.drawable.ic_israel
            context.getString(R.string.inr) -> return R.drawable.ic_india
            context.getString(R.string.isk) -> return R.drawable.ic_iceland
            context.getString(R.string.jpy) -> return R.drawable.ic_japan
            context.getString(R.string.krw) -> return R.drawable.ic_south_korea
            context.getString(R.string.mxn) -> return R.drawable.ic_mexico
            context.getString(R.string.myr) -> return R.drawable.ic_malaysia
            context.getString(R.string.nok) -> return R.drawable.ic_norway
            context.getString(R.string.nzd) -> return R.drawable.ic_new_zealand
            context.getString(R.string.php) -> return R.drawable.ic_philippines
            context.getString(R.string.pln) -> return R.drawable.ic_poland
            context.getString(R.string.ron) -> return R.drawable.ic_romania
            context.getString(R.string.rub) -> return R.drawable.ic_russia
            context.getString(R.string.sek) -> return R.drawable.ic_sweden
            context.getString(R.string.sgd) -> return R.drawable.ic_singapore
            context.getString(R.string.thb) -> return R.drawable.ic_thailand
            context.getString(R.string.usd) -> return R.drawable.ic_united_states
            context.getString(R.string.zar) -> return R.drawable.ic_south_africa
            else -> return R.drawable.ic_flag_placeholder
        }
    }

    fun getName(context: Context, code: String): String {
        when (code) {
            context.getString(R.string.aud) -> return context.getString(R.string.aud_name)
            context.getString(R.string.bgn) -> return context.getString(R.string.bgn_name)
            context.getString(R.string.brl) -> return context.getString(R.string.brl_name)
            context.getString(R.string.cad) -> return context.getString(R.string.cad_name)
            context.getString(R.string.chf) -> return context.getString(R.string.chf_name)
            context.getString(R.string.cny) -> return context.getString(R.string.cny_name)
            context.getString(R.string.czk) -> return context.getString(R.string.czk_name)
            context.getString(R.string.dkk) -> return context.getString(R.string.dkk_name)
            context.getString(R.string.eur) -> return context.getString(R.string.eur_name)
            context.getString(R.string.gbp) -> return context.getString(R.string.gbp_name)
            context.getString(R.string.hkd) -> return context.getString(R.string.hkd_name)
            context.getString(R.string.hrk) -> return context.getString(R.string.hrk_name)
            context.getString(R.string.huf) -> return context.getString(R.string.huf_name)
            context.getString(R.string.idr) -> return context.getString(R.string.idr_name)
            context.getString(R.string.ils) -> return context.getString(R.string.ils_name)
            context.getString(R.string.inr) -> return context.getString(R.string.inr_name)
            context.getString(R.string.isk) -> return context.getString(R.string.isk_name)
            context.getString(R.string.jpy) -> return context.getString(R.string.jpy_name)
            context.getString(R.string.krw) -> return context.getString(R.string.krw_name)
            context.getString(R.string.mxn) -> return context.getString(R.string.mxn_name)
            context.getString(R.string.myr) -> return context.getString(R.string.myr_name)
            context.getString(R.string.nok) -> return context.getString(R.string.nok_name)
            context.getString(R.string.nzd) -> return context.getString(R.string.nzd_name)
            context.getString(R.string.php) -> return context.getString(R.string.php_name)
            context.getString(R.string.pln) -> return context.getString(R.string.pln_name)
            context.getString(R.string.ron) -> return context.getString(R.string.ron_name)
            context.getString(R.string.rub) -> return context.getString(R.string.rub_name)
            context.getString(R.string.sek) -> return context.getString(R.string.sek_name)
            context.getString(R.string.sgd) -> return context.getString(R.string.sgd_name)
            context.getString(R.string.thb) -> return context.getString(R.string.thb_name)
            context.getString(R.string.usd) -> return context.getString(R.string.usd_name)
            context.getString(R.string.zar) -> return context.getString(R.string.zar_name)
            else -> return context.getString(R.string.currency_name_placeholder)
        }
    }
}