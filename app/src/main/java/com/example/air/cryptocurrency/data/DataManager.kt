package com.example.air.cryptocurrency.data

import com.example.air.cryptocurrency.data.api.CurrencyApi
import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse
import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse
import io.reactivex.Observable
import retrofit2.http.Query
import javax.inject.Inject

class DataManager @Inject constructor(var retrofit: CurrencyApi) : IDataManager {

    override fun getAllCurrency(currency:String, limit:String): Observable<ArrayList<AllCurrencyResponse>> {
        return retrofit.getAllCurrencyApi(currency, limit)
    }

    override fun getSelectedCurrency(idCurrency: String, currency:String): Observable<ArrayList<SelectedCurrencyResponse>> {
        return retrofit.getSelectedCurrencyApi(idCurrency, currency)
    }
}