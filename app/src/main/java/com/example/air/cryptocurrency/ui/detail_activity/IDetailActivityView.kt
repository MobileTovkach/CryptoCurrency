package com.example.air.cryptocurrency.ui.detail_activity

import com.example.air.cryptocurrency.model.selected_currency.SelectedCurrencyResponse
import com.example.air.cryptocurrency.ui.base.IBaseView

interface IDetailActivityView: IBaseView{

    fun Success(listCurrency: ArrayList<SelectedCurrencyResponse>)

}