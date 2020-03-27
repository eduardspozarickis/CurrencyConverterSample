package com.pozarickis.currencyconvertersample.feature.currency.dependency

import androidx.lifecycle.ViewModelProvider
import com.pozarickis.currencyconvertersample.feature.currency.CurrencyFragment
import com.pozarickis.currencyconvertersample.feature.currency.CurrencyViewModel
import com.pozarickis.currencyconvertersample.feature.currency.api.CurrencyService
import com.pozarickis.currencyconvertersample.feature.currency.repository.CurrencyRepo
import com.pozarickis.currencyconvertersample.feature.currency.repository.CurrencyRepoImpl
import com.pozarickis.currencyconvertersample.feature.currency.ui.CurrencyAdapter
import com.pozarickis.currencyconvertersample.feature.util.scopes.FeatureScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CurrencyModule {

    @FeatureScope
    @Provides
    fun provideService(retrofit: Retrofit): CurrencyService {
        return retrofit.create(CurrencyService::class.java)
    }

    @FeatureScope
    @Provides
    fun provideRepository(apiService: CurrencyService): CurrencyRepo {
        return CurrencyRepoImpl(apiService)
    }

    @FeatureScope
    @Provides
    fun provideViewModelFactory(
        repository: CurrencyRepo
    ): CurrencyViewModel.ViewModelFactory {
        return CurrencyViewModel.ViewModelFactory(repository)
    }

    @FeatureScope
    @Provides
    fun provideViewModel(
        factory: CurrencyViewModel.ViewModelFactory,
        fragment: CurrencyFragment
    ): CurrencyViewModel {
        return ViewModelProvider(fragment, factory).get(CurrencyViewModel::class.java)
    }

    @FeatureScope
    @Provides
    fun provideAdapter(viewModel: CurrencyViewModel): CurrencyAdapter {
        return CurrencyAdapter(viewModel.itemClickListener)
    }
}