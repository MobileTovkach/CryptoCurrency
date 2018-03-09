package com.example.air.cryptocurrency.ui.detail_activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.air.cryptocurrency.R
import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse
import com.example.air.cryptocurrency.ui.base.BaseActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), IDetailActivityView {

    private val TAG = DetailActivity::class.java.simpleName

    companion object {
        var idCurrency = ""

        fun setData(id: String){
            idCurrency = id
        }

        fun getStartIntent(context: Context): Intent {
            return Intent(context, DetailActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter : DetailActivityPresenter<IDetailActivityView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        getActivityComponents().inject(this)
        presenter.onAttach(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.getSelectedCurrency(idCurrency, Prefs.getString("currency", "USD"))
    }

    @SuppressLint("SetTextI18n")
    override fun Success(listCurrency: ArrayList<SelectedCurrencyResponse>) {
        title = listCurrency[0].name
        rank_value.text = listCurrency[0].rank
        name_value.text = listCurrency[0].name
        symbol_value.text = listCurrency[0].symbol
        when(Prefs.getString("currency", "USD")){
            "USD" -> price_value.text = listCurrency[0].jsonMember24hVolumeUsd
            "EUR" -> price_value.text = listCurrency[0].jsonMember24hVolumeEur
            "CNY" -> price_value.text = listCurrency[0].jsonMember24hVolumeCny
        }

        price_btc.text = listCurrency[0].priceBtc

        if(listCurrency[0].percentChange1h!!.contains("-")) {
            value_hour.setTextColor(Color.RED)
        } else {
            value_hour.setTextColor(Color.GREEN)
        }
        value_hour.text = listCurrency[0].percentChange1h + "%"

        if(listCurrency[0].percentChange24h!!.contains("-")) {
            value_day.setTextColor(Color.RED)
        } else {
            value_day.setTextColor(Color.GREEN)
        }
        value_day.text = listCurrency[0].percentChange24h + "%"

        if(listCurrency[0].percentChange24h!!.contains("-")) {
            value_week.setTextColor(Color.RED)
        } else {
            value_week.setTextColor(Color.GREEN)
        }
        value_week.text = listCurrency[0].percentChange7d + "%"

        total_value.text = listCurrency[0].totalSupply

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.refresh_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId === android.R.id.home) {
            finish()
        } else if(item?.itemId == R.id.action_refresh){
            presenter.getSelectedCurrency(idCurrency, Prefs.getString("currency", "USD"))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}
