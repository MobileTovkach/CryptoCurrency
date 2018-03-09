package com.example.air.cryptocurrency.ui.setting_screen

import com.example.air.cryptocurrency.ui.base.IBasePresenter

interface ISettingActivityPresenter <in V: ISettingActivityView>: IBasePresenter<V>{

    fun setUsdCurrency()

    fun setEurCurrency()

    fun setCnyCurrency()

}