package com.example.air.cryptocurrency.ui.main_activity

import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.model.currency_list.ArrayCurrency
import com.example.air.cryptocurrency.ui.base.BasePresenter
import com.example.air.cryptocurrency.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivityPresenter <V : IMainActivityView> @Inject
constructor(schedulerProvider: ISchedulerProvider?,
            compositeDisposable: CompositeDisposable?,
            dataManager: IDataManager?) : BasePresenter<V>(schedulerProvider!! , compositeDisposable!! , dataManager!!) , IMainActivityPresenter<V> {

    override fun getCurrencyList(currency:String, limit:String) {
        dataManager.getAllCurrency(currency, limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listCurrency ->
                    ArrayCurrency.listCurrency = listCurrency
                    mvpView?.Success()
                }, {
                    throwable: Throwable -> mvpView?.onError(throwable.stackTrace.toString())
                })
    }

}