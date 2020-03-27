package com.pozarickis.currencyconvertersample.feature.currency.repository

import com.pozarickis.currencyconvertersample.feature.currency.api.CurrencyService
import com.pozarickis.currencyconvertersample.feature.currency.model.Currency
import com.pozarickis.currencyconvertersample.feature.util.networking.ApiError
import com.pozarickis.currencyconvertersample.feature.util.networking.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.callbackFlow
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
class CurrencyRepoImpl(
    private val service: CurrencyService
) : CurrencyRepo {

    override suspend fun fetchCurrencies(baseCurrency: String) = callbackFlow {
        runCatching {
            val response = service.getLatestCurrency(baseCurrency)

            if (!response.isSuccessful) {
                val error = when (response.code()) {
                    // TODO: return specific error if server provides such
                    else -> ApiError.Other
                }

                offer(ApiResponse(error, emptyList<Currency>()))
                return@callbackFlow
            }

            val body = response.body()
            if (body == null) {
                offer(ApiResponse(ApiError.Other, emptyList<Currency>()))
                return@callbackFlow
            }

            val data = body.rates.map { rate ->
                Currency(rate.key, rate.value)
            }
            offer(ApiResponse(data = data))

        }.recover { exception: Throwable ->
            val error = when (exception) {
                is UnknownHostException,
                is SocketTimeoutException -> ApiError.Connection
                else -> ApiError.Other
            }

            offer(ApiResponse(error, emptyList<Currency>()))
        }
    }
}