package com.pozarickis.currencyconvertersample.feature.currency.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pozarickis.currencyconvertersample.R

class CurrencyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    companion object {

        fun build(context: Context, parent: ViewGroup): CurrencyViewHolder {
            val inflater = LayoutInflater.from(context)
            val itemView = inflater.inflate(R.layout.fragment_currency_li, parent, false)

            return CurrencyViewHolder(itemView)
        }
    }

    private val flagIconView = itemView.findViewById<ImageView>(R.id.currency_flag)
    private val currencyTitleView = itemView.findViewById<TextView>(R.id.currency_title)
    private val currencyValueView = itemView.findViewById<TextView>(R.id.currency_value)
    private val currencyRateView = itemView.findViewById<TextView>(R.id.currency_rate)

    fun init(
        item: CurrencyItem,
        itemClickListener: CurrencyAdapter.ItemClickListener
    ) {
        val context = itemView.context

        itemView.setOnClickListener {
            itemClickListener.onCurrencyClicked(item.currency)
        }

        initFlag(item.flagResId)
        initTitle(item.currency)
        initValue(item.value)
        initRate(context, item.currency, item.rate, item.baseCurrency)
    }

    private fun initFlag(flagResId: Int) {
        flagIconView.setImageResource(flagResId)
    }

    private fun initTitle(title: String) {
        currencyTitleView.text = title
    }

    private fun initValue(value: Float) {
        currencyValueView.text = value.toString()
    }

    private fun initRate(context: Context, currency: String, rate: Float, baseCurrency: String) {
        val rateText = context.getString(R.string.currency_rate, currency, rate, baseCurrency)
        currencyRateView.text = rateText
    }
}