package com.example.air.cryptocurrency.di.component

import android.content.Context
import com.example.air.cryptocurrency.app.CurrencyApp
import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.di.modul.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface ApplicationComponent {

    fun inject(app: CurrencyApp)

    fun context(): Context

    fun dataManager(): IDataManager

    fun application(): CurrencyApp
}