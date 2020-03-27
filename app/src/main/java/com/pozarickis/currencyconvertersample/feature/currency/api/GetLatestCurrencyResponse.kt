package com.pozarickis.currencyconvertersample.feature.currency.api

data class GetLatestCurrencyResponse(
    val rates: Map<String, Float>,
    val base: String,
    val date: String
)