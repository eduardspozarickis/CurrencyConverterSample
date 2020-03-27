package com.pozarickis.currencyconvertersample.feature.currency.dependency

import com.pozarickis.currencyconvertersample.app.dependency.DataAccessComponent
import com.pozarickis.currencyconvertersample.feature.currency.CurrencyFragment
import com.pozarickis.currencyconvertersample.feature.util.scopes.FeatureScope
import dagger.BindsInstance
import dagger.Component

@FeatureScope
@Component(
    dependencies = [
        DataAccessComponent::class
    ],
    modules = [
        CurrencyModule::class
    ]
)
interface CurrencyComponent {

    fun inject(fragment: CurrencyFragment)

    @Component.Factory
    interface Factory {
        fun create(
            dataAccessComponent: DataAccessComponent,
            @BindsInstance fragment: CurrencyFragment
        ): CurrencyComponent
    }
}