package com.pozarickis.currencyconvertersample.feature.currency.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyService {

    @GET("/latest")
    suspend fun getLatestCurrency(
        @Query("base") base: String
    ): Response<GetLatestCurrencyResponse>
}