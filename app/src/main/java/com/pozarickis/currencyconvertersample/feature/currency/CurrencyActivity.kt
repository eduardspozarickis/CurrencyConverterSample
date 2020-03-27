package com.pozarickis.currencyconvertersample.feature.currency

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pozarickis.currencyconvertersample.R

class CurrencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currencies)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, CurrencyFragment.newInstance())
                .commit()
        }
    }
}
