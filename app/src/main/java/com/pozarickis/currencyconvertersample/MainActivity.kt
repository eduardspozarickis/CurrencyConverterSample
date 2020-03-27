package com.pozarickis.currencyconvertersample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.pozarickis.currencyconvertersample.feature.currency.CurrencyActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this, CurrencyActivity::class.java))
        finish()
    }
}
