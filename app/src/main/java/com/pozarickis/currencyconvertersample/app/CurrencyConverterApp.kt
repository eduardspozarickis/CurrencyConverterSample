package com.pozarickis.currencyconvertersample.app

import android.app.Application
import com.pozarickis.currencyconvertersample.BuildConfig
import com.pozarickis.currencyconvertersample.app.dependency.DaggerDataAccessComponent
import com.pozarickis.currencyconvertersample.app.dependency.DataAccessComponent
import timber.log.Timber
import timber.log.Timber.DebugTree




class CurrencyConverterApp : Application() {

    companion object {
        lateinit var instance: CurrencyConverterApp
            private set
    }

    lateinit var dataAccessComponent: DataAccessComponent

    override fun onCreate() {
        super.onCreate()

        instance = this

        initDataAccessComponent()
        initTimberLogging()
    }

    private fun initDataAccessComponent() {
        dataAccessComponent = DaggerDataAccessComponent
            .factory()
            .create(applicationContext)
    }

    private fun initTimberLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}