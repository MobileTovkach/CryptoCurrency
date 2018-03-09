package com.example.air.cryptocurrency.di.modul

import android.content.Context
import com.example.air.cryptocurrency.R
import com.example.air.cryptocurrency.app.CurrencyApp
import com.example.air.cryptocurrency.data.api.CurrencyApi
import com.example.air.cryptocurrency.data.DataManager
import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.utils.AppConstants
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uk.co.chrisjenx.calligraphy.CalligraphyConfig
import javax.inject.Singleton

@Module
class AppModule(private val application: CurrencyApp){

    @Provides
    @Singleton
    fun provideApplicationContext() : Context = application

    @Provides
    @Singleton
    fun provideApplication() : CurrencyApp = application

    @Provides
    @Singleton
    fun provideCalligraphyDefaultConfig(): CalligraphyConfig {
        return CalligraphyConfig.Builder()
                .setDefaultFontPath("")
                .setFontAttrId(R.attr.fontPath)
                .build()
    }

    @Provides
    @Singleton
    fun provideDataManager(dataManager: DataManager) : IDataManager {
        return dataManager
    }

    @Provides
    @Singleton
    fun provideRetrofitDefaultConfig() : CurrencyApi {
        val rxAdapter = RxJava2CallAdapterFactory.create()
        val okHttpClient = OkHttpClient.Builder()
                .addNetworkInterceptor(StethoInterceptor())
                .build()
        val json = GsonBuilder().create()
        val retrofit = Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(json))
                .addCallAdapterFactory(rxAdapter)
                .build()
        return retrofit.create(CurrencyApi::class.java)
    }
}