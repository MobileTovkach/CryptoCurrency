package com.example.air.cryptocurrency.app

import android.app.Application
import android.content.ContextWrapper
import com.androidnetworking.AndroidNetworking
import com.example.air.cryptocurrency.di.component.ApplicationComponent
import com.example.air.cryptocurrency.di.component.DaggerApplicationComponent
import com.example.air.cryptocurrency.di.modul.AppModule
import com.facebook.stetho.Stetho
import com.pixplicity.easyprefs.library.Prefs
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Inject

class CurrencyApp : Application() {

    @Inject
    lateinit var calligraphyConfig: CalligraphyConfig

    private lateinit var appComponent : ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerApplicationComponent.builder()
                .appModule(AppModule(this)).build()

        appComponent.inject(this)

        AndroidNetworking.initialize(applicationContext)
        CalligraphyConfig.initDefault(calligraphyConfig)
        Stetho.initializeWithDefaults(this)
        Prefs.Builder()
                .setContext(this)
                .setMode(ContextWrapper.MODE_PRIVATE)
                .setPrefsName(packageName)
                .setUseDefaultSharedPreference(true)
                .build()
    }

    fun getComponent() : ApplicationComponent {
        return appComponent
    }


}