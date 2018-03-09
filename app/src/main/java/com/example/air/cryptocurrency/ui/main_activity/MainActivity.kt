package com.example.air.cryptocurrency.ui.main_activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import com.example.air.cryptocurrency.R
import com.example.air.cryptocurrency.adapter.CurrencyAdapter
import com.example.air.cryptocurrency.adapter.CurrencyHolder
import com.example.air.cryptocurrency.model.currency_list.AllCurrencyResponse
import com.example.air.cryptocurrency.model.currency_list.ArrayCurrency
import com.example.air.cryptocurrency.ui.base.BaseActivity
import com.example.air.cryptocurrency.ui.detail_activity.DetailActivity
import com.example.air.cryptocurrency.ui.setting_screen.SettingActivity
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : BaseActivity(), IMainActivityView, SwipeRefreshLayout.OnRefreshListener, CurrencyHolder.RangCallback {

    val currencyAdapter = CurrencyAdapter(this)

    private val TAG = MainActivity::class.java.simpleName

    companion object {
        fun getStartIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    @Inject
    lateinit var presenter : MainActivityPresenter<IMainActivityView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getActivityComponents().inject(this)
        presenter.onAttach(this)
        presenter.getCurrencyList(Prefs.getString("currency", "USD"), Prefs.getString("limit", "100"))
        swipeRefreshLayout.setOnRefreshListener(this)
        swipeRefreshLayout.isRefreshing = true
        recycler_currency.layoutManager = LinearLayoutManager(this)
        recycler_currency.adapter = currencyAdapter
    }

    override fun onRefresh() {
        swipeRefreshLayout.isRefreshing = true
        presenter.getCurrencyList(Prefs.getString("currency", "USD"), Prefs.getString("limit", "100"))
    }

    override fun Success() {
        swipeRefreshLayout.isRefreshing = false
        currencyAdapter.setData(ArrayCurrency.listCurrency)
        currencyAdapter.notifyDataSetChanged()
    }

    override fun onError(errorString: String) {
        swipeRefreshLayout.isRefreshing = false
        super.onError(errorString)
    }

    override fun getPosition(rank: String) {
        for(item in ArrayCurrency.listCurrency){
            if(item.rank == rank){
                DetailActivity.setData(item.id!!)
                startActivity(DetailActivity.getStartIntent(this))
                break
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main_menu, menu)
        val myActionMenuItem = menu.findItem(R.id.action_search)
        val searchView = myActionMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                val list: ArrayList<AllCurrencyResponse> = ArrayList()
                ArrayCurrency.listCurrency.filterTo(list) { it.symbol!!.toLowerCase().contains(newText.toLowerCase()) }
                currencyAdapter.setData(list)
                currencyAdapter.notifyDataSetChanged()
                list.clear()
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()

        return if (id == R.id.action_search) {
            true
        } else if(id == R.id.action_setting){
            startActivity(SettingActivity.getStartIntent(this))
            return true
        } else super.onOptionsItemSelected(item)

    }

}
