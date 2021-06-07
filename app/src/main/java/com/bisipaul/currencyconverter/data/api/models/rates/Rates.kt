package com.bisipaul.currencyconverter.data.api.models.rates

/**
 *  Created by paulbisioc on 05.06.2021
 */

data class Rates(
    val AUD: Double?,
    val BGN: Double?,
    val BRL: Double?,
    val CAD: Double?,
    val CHF: Double?,
    val CNY: Double?,
    val CZK: Double?,
    val DKK: Double?,
    val EUR: Double?,
    val GBP: Double?,
    val HKD: Double?,
    val HRK: Double?,
    val HUF: Double?,
    val IDR: Double?,
    val ILS: Double?,
    val INR: Double?,
    val ISK: Double?,
    val JPY: Double?,
    val KRW: Double?,
    val MXN: Double?,
    val MYR: Double?,
    val NOK: Double?,
    val NZD: Double?,
    val PHP: Double?,
    val PLN: Double?,
    val RON: Double?,
    val RUB: Double?,
    val SEK: Double?,
    val SGD: Double?,
    val THB: Double?,
    val USD: Double?,
    val ZAR: Double?
) {
    constructor(list: List<Double>) : this (
        list[0], list[1], list[2], list[3], list[4], list[5], list[6], list[7], null, list[8], list[9], list[10], list[11],
        list[12], list[13], list[14], list[15], list[16], list[17], list[18], list[19], list[20], list[21],
        list[22], list[23], list[24], list[25], list[26], list[27], list[28], list[29], list[30]
    )

    fun toList(): MutableList<Rate> {
        val list = mutableListOf<Rate>()
        AUD?.let {
            list.addRate("AUD", AUD)
        }

        BGN?.let {
            list.addRate("BGN", BGN)
        }

        BRL?.let {
            list.addRate("BRL", BRL)
        }

        CAD?.let {
            list.addRate("CAD", CAD)
        }

        CHF?.let {
            list.addRate("CHF", CHF)
        }

        CNY?.let {
            list.addRate("CNY", CNY)
        }

        CZK?.let {
            list.addRate("CZK", CZK)
        }

        DKK?.let {
            list.addRate("DKK", DKK)
        }

        EUR?.let {
            list.addRate("EUR", EUR)
        }

        GBP?.let {
            list.addRate("GBP", GBP)
        }

        HKD?.let {
            list.addRate("HKD", HKD)
        }

        HRK?.let {
            list.addRate("HRK", HRK)
        }

        HUF?.let {
            list.addRate("HUF", HUF)
        }

        IDR?.let {
            list.addRate("IDR", IDR)
        }

        ILS?.let {
            list.addRate("ILS", ILS)
        }

        INR?.let {
            list.addRate("INR", INR)
        }

        ISK?.let {
            list.addRate("ISK", ISK)
        }

        JPY?.let {
            list.addRate("JPY", JPY)
        }

        KRW?.let {
            list.addRate("KRW", KRW)
        }

        MXN?.let {
            list.addRate("MXN", MXN)
        }

        MYR?.let {
            list.addRate("MYR", MYR)
        }

        NOK?.let {
            list.addRate("NOK", NOK)
        }

        NZD?.let {
            list.addRate("NZD", NZD)
        }

        PHP?.let {
            list.addRate("PHP", PHP)
        }

        PLN?.let {
            list.addRate("PLN", PLN)
        }

        RON?.let {
            list.addRate("RON", RON)
        }

        RUB?.let {
            list.addRate("RUB", RUB)
        }

        SEK?.let {
            list.addRate("SEK", SEK)
        }

        SGD?.let {
            list.addRate("SGD", SGD)
        }

        THB?.let {
            list.addRate("THB", THB)
        }

        USD?.let {
            list.addRate("USD", USD)
        }

        ZAR?.let {
            list.addRate("ZAR", ZAR)
        }

        return list
    }

    private fun MutableList<Rate>.addRate(symbol: String, value: Double) {
        this.add(Rate(symbol, value))
    }
}