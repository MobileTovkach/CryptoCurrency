package com.example.air.cryptocurrency.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.air.cryptocurrency.R
import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse
import com.pixplicity.easyprefs.library.Prefs
import java.text.DecimalFormat

class CurrencyAdapter(var callback: CurrencyHolder.RangCallback) : RecyclerView.Adapter<CurrencyHolder>() {

    private var data: ArrayList<AllCurrencyResponse> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CurrencyHolder {
        val inflatedView = LayoutInflater.from(parent?.context)
                .inflate(R.layout.currency_list_item, parent , false)
        return CurrencyHolder(inflatedView, callback)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CurrencyHolder?, position: Int) {
        holder?.rank_currency?.text = data[position].rank
        holder?.data_currency?.text = data[position].symbol
        if(Prefs.getString("currency", "USD") == "USD") {
            holder?.data_price?.text = (data[position].priceUsd?.toDouble()?.format(2)) + " USD"
        } else if(Prefs.getString("currency", "USD") == "EUR"){
            holder?.data_price?.text = (data[position].priceEur?.toDouble()?.format(2)) + " EUR"
        } else if(Prefs.getString("currency", "USD") == "CNY"){
            holder?.data_price?.text = (data[position].priceCny?.toDouble()?.format(2)) + " CNY"
        }

        if (data[position].percentChange24h!!.contains("-")) {
            holder?.data_percent?.setTextColor(Color.RED)
            holder?.data_percent?.text = data[position].percentChange24h + "%"
        } else {
            holder?.data_percent?.setTextColor(Color.GREEN)
            holder?.data_percent?.text = data[position].percentChange24h + "%"
        }
    }

    fun setData(list:ArrayList<AllCurrencyResponse>){
        data.clear()
        data.addAll(list)
    }

    private fun Double.format(fracDigits: Int): String {
        val df = DecimalFormat()
        df.maximumFractionDigits = fracDigits
        return df.format(this)
    }

}