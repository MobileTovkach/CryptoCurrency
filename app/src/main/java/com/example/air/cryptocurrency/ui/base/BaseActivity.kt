package com.example.air.cryptocurrency.ui.base

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.air.cryptocurrency.app.CurrencyApp
import com.example.air.cryptocurrency.di.component.ActivityComponent
import com.example.air.cryptocurrency.di.component.DaggerActivityComponent
import com.example.air.cryptocurrency.di.modul.ActivityModule
import com.example.air.cryptocurrency.utils.NetworkUtils
import timber.log.Timber
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

open class BaseActivity : AppCompatActivity(), IBaseView {

    private lateinit var activityComponent : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent((application as CurrencyApp).getComponent())
                .build()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun getActivityComponents(): ActivityComponent {
        return activityComponent
    }

    override fun networkConnected(): Boolean {
        return NetworkUtils.networkConnected(applicationContext)
    }

    fun onFragmentAttached() {

    }

    override fun onError(resId: Int) {
        Timber.d("onError %s", getString(resId))
    }

    override fun onError(errorString: String) {
        Timber.d("onError %s", errorString)
    }

}