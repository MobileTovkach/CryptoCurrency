package com.example.air.cryptocurrency.ui.base

import com.androidnetworking.error.ANError

interface IBasePresenter<in V : IBaseView> {

    fun onAttach(view: V)

    fun onDetach()

    fun handleApiError(error: ANError)
}