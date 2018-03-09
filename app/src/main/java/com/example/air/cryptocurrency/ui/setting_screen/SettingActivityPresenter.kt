package com.example.air.cryptocurrency.ui.setting_screen

import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.ui.base.BasePresenter
import com.example.air.cryptocurrency.utils.rx.ISchedulerProvider
import com.pixplicity.easyprefs.library.Prefs
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class SettingActivityPresenter<V : ISettingActivityView> @Inject
constructor(schedulerProvider: ISchedulerProvider,
            compositeDisposable: CompositeDisposable,
            dataManager: IDataManager) : BasePresenter<V>(schedulerProvider , compositeDisposable , dataManager) , ISettingActivityPresenter<V> {

    override fun setUsdCurrency() {
        Prefs.putString("currency", "USD")
    }

    override fun setEurCurrency() {
        Prefs.putString("currency", "EUR")
    }

    override fun setCnyCurrency() {
        Prefs.putString("currency", "CNY")
    }

}