package com.example.air.cryptocurrency.ui.main_activity

import com.example.air.cryptocurrency.ui.base.IBasePresenter

interface IMainActivityPresenter<in V: IMainActivityView>: IBasePresenter<V> {

    fun getCurrencyList(currency:String, limit:String)

}