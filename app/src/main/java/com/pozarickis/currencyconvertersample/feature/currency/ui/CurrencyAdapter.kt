package com.pozarickis.currencyconvertersample.feature.currency.ui

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class CurrencyAdapter(
    private val itemClickListener: ItemClickListener
) : ListAdapter<CurrencyItem, CurrencyViewHolder>(RateDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        return CurrencyViewHolder.build(parent.context, parent)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.init(getItem(position), itemClickListener)
    }

    interface ItemClickListener {
        fun onCurrencyClicked(currency: String)
    }

    class RateDiff : DiffUtil.ItemCallback<CurrencyItem>() {

        override fun areItemsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: CurrencyItem, newItem: CurrencyItem): Boolean {
            return oldItem.value == newItem.value &&
                    oldItem.rate == newItem.rate
        }
    }
}