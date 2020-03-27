package com.pozarickis.currencyconvertersample.feature.currency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pozarickis.currencyconvertersample.R
import com.pozarickis.currencyconvertersample.app.CurrencyConverterApp
import com.pozarickis.currencyconvertersample.feature.currency.dependency.DaggerCurrencyComponent
import com.pozarickis.currencyconvertersample.feature.currency.ui.CurrencyAdapter
import com.pozarickis.currencyconvertersample.feature.currency.ui.CurrencyItem
import javax.inject.Inject

class CurrencyFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyFragment()
    }

    @Inject
    lateinit var viewModel: CurrencyViewModel

    @Inject
    lateinit var adapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_currencies, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initDependencies()
        initObservers()
    }

    private fun initDependencies() {
        DaggerCurrencyComponent.factory()
            .create(
                CurrencyConverterApp.instance.dataAccessComponent,
                this
            )
            .inject(this)
    }

    private fun initObservers() {
        viewModel.currencyLiveData.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer

            handleData(it)
        })
    }

    private fun handleData(items: List<CurrencyItem>) {
        adapter.submitList(items)
    }
}