package com.example.air.cryptocurrency.ui.base

import android.util.Log
import com.androidnetworking.common.ANConstants
import com.androidnetworking.error.ANError
import com.example.air.cryptocurrency.data.IDataManager
import com.example.air.cryptocurrency.data.api.ApiError
import com.example.air.cryptocurrency.utils.AppConstants
import com.example.air.cryptocurrency.utils.rx.ISchedulerProvider
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject
import javax.net.ssl.HttpsURLConnection

open class BasePresenter<V : IBaseView> @Inject
constructor(val schedulerProvider: ISchedulerProvider,
            private val compositeDisposable: CompositeDisposable,
            val dataManager: IDataManager) : IBasePresenter<V> {

    var mvpView: V? = null
        private set

    override fun onAttach(view: V) {
        mvpView = view
    }

    override fun onDetach() {
        compositeDisposable.dispose()
        mvpView = null
    }

    override fun handleApiError(error: ANError) {
        if (error.errorBody.isNullOrEmpty()) {
            mvpView!!.onError("Error")
            return
        }

        if (error.errorCode.equals(AppConstants.API_STATUS_CODE_LOCAL_ERROR) && error.errorDetail.equals(ANConstants.CONNECTION_ERROR)) {
            mvpView!!.onError("Connection error")
            return
        }

        if (error.errorCode.equals(AppConstants.API_STATUS_CODE_LOCAL_ERROR) && error.errorDetail.equals(ANConstants.REQUEST_CANCELLED_ERROR)) {
            mvpView!!.onError("Retry error")
            return
        }

        val builder = GsonBuilder().excludeFieldsWithoutExposeAnnotation()
        val json = builder.create()

        try {
            val apiError = json.fromJson<ApiError>(error.errorBody , ApiError::class.java)

            if (apiError?.message.isNullOrEmpty()) {
                mvpView!!.onError("Default error")
                return
            }

            when (error.errorCode) {
                HttpsURLConnection.HTTP_INTERNAL_ERROR , HttpsURLConnection.HTTP_NOT_FOUND -> mvpView!!.onError(apiError.message!!)
                else -> mvpView!!.onError(apiError.message!!)
            }
        } catch (e: JsonSyntaxException) {
            Log.e(TAG , "handleApiError" , e)
            mvpView!!.onError("Error")
        } catch (e: NullPointerException) {
            Log.e(TAG , "handleApiError" , e)
            mvpView!!.onError("Error")
        }

    }

    companion object {
        private val TAG = "BasePresenter"
    }

}