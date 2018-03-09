package com.example.air.cryptocurrency.ui.detail_activity

import com.example.air.cryptocurrency.ui.base.IBasePresenter

interface IDetailActivityPresenter<in V: IDetailActivityView> : IBasePresenter<V> {

    fun getSelectedCurrency(idCurrency: String, currency:String)

}