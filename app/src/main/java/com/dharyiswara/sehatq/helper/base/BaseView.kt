package com.dharyiswara.sehatq.helper.base

import com.dharyiswara.sehatq.helper.Resource
import com.dharyiswara.sehatq.helper.Status

interface BaseView {

    fun getLayoutResId(): Int

    fun initView()

    fun initEvent()

    fun loadingData(isFromSwipe: Boolean = false)

    fun observeData()

    fun startLoading()

    fun stopLoading()

    fun onInternetError()

    fun onEmptyResult()

    fun onError(throwable: Throwable? = null)

    fun <T> parseObserveData(
        resource: Resource<T>,
        resultLoading: (T?) -> Unit = { startLoading() },
        resultSuccess: (T) -> Unit = { _: T -> },
        resultNetworkFailed: (Throwable?) -> Unit = { onInternetError() },
        resultEmpty: () -> Unit = { onEmptyResult() },
        resultError: (Throwable?) -> Unit = { onError(it) }
    ) {
        when (resource.status) {
            Status.LOADING -> {
                resultLoading(resource.data)
            }
            Status.SUCCESS -> {
                stopLoading()
                resource.data?.let { resultSuccess(it) }
            }
            Status.NETWORK_FAILED -> {
                stopLoading()
                resultNetworkFailed(resource.throwable)
            }
            Status.EMPTY -> {
                stopLoading()
                resultEmpty()
            }
            Status.ERROR -> {
                stopLoading()
                resultError(resource.throwable)
            }
        }
    }

}