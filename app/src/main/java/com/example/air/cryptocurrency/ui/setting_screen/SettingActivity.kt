package com.example.air.cryptocurrency.ui.setting_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.view.MenuItem
import com.example.air.cryptocurrency.R
import com.example.air.cryptocurrency.ui.base.BaseActivity
import com.example.air.cryptocurrency.ui.detail_activity.DetailActivity
import com.example.air.cryptocurrency.ui.main_activity.MainActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_setting.*
import javax.inject.Inject
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener



class SettingActivity : BaseActivity(), ISettingActivityView {

    private val TAG = SettingActivity::class.java.simpleName

    companion object {

        fun getStartIntent(context: Context): Intent {
            return Intent(context, SettingActivity::class.java)
        }

    }

    @Inject
    lateinit var presenter : SettingActivityPresenter<ISettingActivityView>

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        getActivityComponents().inject(this)
        presenter.onAttach(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Setting"

        when(Prefs.getString("currency", "USD")){
            "USD" -> usd_btn.setBackgroundColor(Color.BLUE)
            "EUR" -> eur_btn.setBackgroundColor(Color.BLUE)
            "CNY" -> cny_btn.setBackgroundColor(Color.BLUE)
        }

        usd_btn.setOnClickListener({
            presenter.setUsdCurrency()
            usd_btn.setBackgroundColor(Color.BLUE)
            setDefaltButtonState()
            usd_btn.setBackgroundColor(Color.BLUE)
        })

        eur_btn.setOnClickListener({
            presenter.setEurCurrency()
            eur_btn.setBackgroundColor(Color.BLUE)
            setDefaltButtonState()
            eur_btn.setBackgroundColor(Color.BLUE)
        })

        cny_btn.setOnClickListener({
            presenter.setCnyCurrency()
            cny_btn.setBackgroundColor(Color.BLUE)
            setDefaltButtonState()
            cny_btn.setBackgroundColor(Color.BLUE)
        })
        seek_bar_limit.progress = Prefs.getString("limit", "100").toInt()
        value_limit.text = Prefs.getString("limit", "100")

        seek_bar_limit.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                value_limit.text = Integer.toString(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}

            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun setDefaltButtonState() {
        usd_btn.background = null
        eur_btn.background = null
        cny_btn.background = null
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId === android.R.id.home) {
            Prefs.putString("limit", value_limit.text.toString())
            finishAffinity()
            startActivity(MainActivity.getStartIntent(this))
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onBackPressed() {
        Prefs.putString("limit", value_limit.text.toString())
        finishAffinity()
        startActivity(MainActivity.getStartIntent(this))
        super.onBackPressed()
    }

}
