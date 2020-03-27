package com.pozarickis.currencyconvertersample.feature.currency.model

import com.pozarickis.currencyconvertersample.R

data class Currency(
    val currency: String,
    val rate: Float
) {

    companion object {
        const val DEFAULT_CURRENCY = "EUR"

        fun getFlagIconResId(currency: String): Int {
            // TODO: import flag resources
            return when (currency) {
                else -> R.drawable.flag_uknown
            }
        }
    }
}
