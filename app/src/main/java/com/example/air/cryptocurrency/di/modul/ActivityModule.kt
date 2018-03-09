package com.example.air.cryptocurrency.di.modul

import android.support.v7.app.AppCompatActivity
import com.example.air.cryptocurrency.di.ActivityScope
import com.example.air.cryptocurrency.ui.detail_activity.DetailActivityPresenter
import com.example.air.cryptocurrency.ui.detail_activity.IDetailActivityPresenter
import com.example.air.cryptocurrency.ui.detail_activity.IDetailActivityView
import com.example.air.cryptocurrency.ui.main_activity.IMainActivityPresenter
import com.example.air.cryptocurrency.ui.main_activity.IMainActivityView
import com.example.air.cryptocurrency.ui.main_activity.MainActivityPresenter
import com.example.air.cryptocurrency.utils.rx.AppSchedulerProvider
import com.example.air.cryptocurrency.utils.rx.ISchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable


@Module
class ActivityModule(private val mActivity: AppCompatActivity) {

    @Provides
    @ActivityScope
    fun provideContext(): AppCompatActivity {
        return mActivity
    }

    @Provides
    @ActivityScope
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @ActivityScope
    fun provideSchedulerProvide(): ISchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    @ActivityScope
    fun provideMainPresenter(presenter: MainActivityPresenter<IMainActivityView>): IMainActivityPresenter<IMainActivityView> {
        return presenter
    }

    @Provides
    @ActivityScope
    fun provideDetailPresenter(presenter: DetailActivityPresenter<IDetailActivityView>): IDetailActivityPresenter<IDetailActivityView> {
        return presenter
    }

}