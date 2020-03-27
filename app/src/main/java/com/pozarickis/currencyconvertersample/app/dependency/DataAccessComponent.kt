package com.pozarickis.currencyconvertersample.app.dependency

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class
    ]
)
interface DataAccessComponent {

    val retrofit: Retrofit

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataAccessComponent
    }
}