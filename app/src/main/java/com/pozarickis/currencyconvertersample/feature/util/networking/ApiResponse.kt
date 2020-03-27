package com.pozarickis.currencyconvertersample.feature.util.networking

class ApiResponse<D>(
    val error: ApiError? = null,
    val data: D? = null
)