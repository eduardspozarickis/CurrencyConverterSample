package com.pozarickis.currencyconvertersample.feature.currency.ui

data class CurrencyItem(
    val flagResId: Int,
    val currency: String,
    val value: Float,
    val rate: Float,
    val baseCurrency: String
)