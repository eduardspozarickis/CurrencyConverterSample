package com.pozarickis.currencyconvertersample.feature.util.networking

sealed class ApiError {
    object Connection : ApiError()
    object Other : ApiError()
}