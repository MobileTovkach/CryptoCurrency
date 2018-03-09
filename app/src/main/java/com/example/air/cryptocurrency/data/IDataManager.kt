package com.example.air.cryptocurrency.data

import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse
import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse
import io.reactivex.Observable
import kotlin.collections.ArrayList

interface IDataManager {

    fun getAllCurrency(currency:String, limit:String): Observable<ArrayList<AllCurrencyResponse>>

    fun getSelectedCurrency(idCurrency: String, currency:String): Observable<ArrayList<SelectedCurrencyResponse>>

}