package com.example.air.cryptocurrency.data.api

import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse
import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyApi {

    @GET("ticker")
    fun getAllCurrencyApi(@Query("convert") currency:String,
                          @Query("limit") limit:String): Observable<ArrayList<AllCurrencyResponse>>

    @GET("ticker/{id}")
    fun getSelectedCurrencyApi(@Path("id") id:String,
                               @Query("convert") currency:String): Observable<ArrayList<SelectedCurrencyResponse>>

}