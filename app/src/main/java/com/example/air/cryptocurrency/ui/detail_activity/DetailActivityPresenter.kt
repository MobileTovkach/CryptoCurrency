package com.example.air.cryptocurrency.ui.detail_activity

import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.ui.base.BasePresenter
import com.example.air.cryptocurrency.utils.rx.ISchedulerProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailActivityPresenter<V : IDetailActivityView> @Inject
constructor(schedulerProvider: ISchedulerProvider,
            compositeDisposable: CompositeDisposable,
            dataManager: IDataManager) : BasePresenter<V>(schedulerProvider , compositeDisposable , dataManager) , IDetailActivityPresenter<V> {

    override fun getSelectedCurrency(idCurrency: String, currency:String) {
        dataManager.getSelectedCurrency(idCurrency, currency)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listCurrency ->
                    mvpView?.Success(listCurrency)
                }, {
                    throwable: Throwable -> mvpView?.onError(throwable.stackTrace.toString())
                })
    }
}