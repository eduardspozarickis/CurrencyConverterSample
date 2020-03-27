package com.pozarickis.currencyconvertersample.feature.currency

import androidx.lifecycle.*
import com.pozarickis.currencyconvertersample.feature.currency.model.Currency
import com.pozarickis.currencyconvertersample.feature.currency.repository.CurrencyRepo
import com.pozarickis.currencyconvertersample.feature.currency.ui.CurrencyAdapter
import com.pozarickis.currencyconvertersample.feature.currency.ui.CurrencyItem
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CurrencyViewModel(
    private val repository: CurrencyRepo
) : ViewModel() {

    var baseCurrency = Currency.DEFAULT_CURRENCY
    var valueToConvert = 0f

    private val _currencyLiveData = MutableLiveData<List<CurrencyItem>>()
    val currencyLiveData: LiveData<List<CurrencyItem>> = _currencyLiveData

    init {
        fetchCurrencies()
    }

    private fun fetchCurrencies() {
        viewModelScope.launch {
            repository.fetchCurrencies(baseCurrency).collect { response ->
                response.data?.let { data ->
                    val items = buildItems(data)

                    _currencyLiveData.postValue(items)
                }

                response.error?.let {
                    // TODO: inform UI about the error
                }
            }
        }
    }

    private fun buildItems(data: List<Currency>): List<CurrencyItem> {
        val items = mutableListOf<CurrencyItem>()

        data.map {
            val flagResId = Currency.getFlagIconResId(it.currency)
            val value = valueToConvert * it.rate

            CurrencyItem(flagResId, it.currency, value, it.rate, baseCurrency)
        }

        return items
    }

    class ViewModelFactory(
        private val repository: CurrencyRepo
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
                return CurrencyViewModel(repository) as T
            }

            throw IllegalArgumentException("unknown ViewModel class")
        }
    }

    val itemClickListener = object : CurrencyAdapter.ItemClickListener {

        override fun onCurrencyClicked(currency: String) {
            baseCurrency = currency

            fetchCurrencies()
        }
    }
}
