package com.pozarickis.currencyconvertersample.feature.currency.repository

import com.pozarickis.currencyconvertersample.feature.currency.model.Currency
import com.pozarickis.currencyconvertersample.feature.util.networking.ApiResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyRepo {

    suspend fun fetchCurrencies(baseCurrency: String): Flow<ApiResponse<List<Currency>>>
}