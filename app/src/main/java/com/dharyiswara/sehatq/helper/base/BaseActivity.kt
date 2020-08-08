package com.dharyiswara.sehatq.helper.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dharyiswara.sehatq.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity(), BaseView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initEvent()
        loadingData()
        observeData()
    }

    override fun initView() {}

    override fun initEvent() {}

    override fun loadingData(isFromSwipe: Boolean) {}

    override fun observeData() {}

    override fun startLoading() {}

    override fun stopLoading() {}

    override fun onEmptyResult() {}

    override fun onInternetError() {
        displayMessage(getString(R.string.error_no_internet))
    }

    override fun onError(throwable: Throwable?) {
        displayMessage(getString(R.string.error_general))
    }

    private fun displayMessage(message: String?) {
        getParentViewGroup()?.let { vg ->
            message?.let { Snackbar.make(vg, it, Snackbar.LENGTH_SHORT).show() }
        }
    }

    private fun decorViewGroup(): ViewGroup? {
        return when (isDecorViewGroup()) {
            true -> window.decorView as ViewGroup
            else -> null
        }
    }

    private fun isDecorViewGroup(): Boolean {
        return window.decorView is ViewGroup
    }

    private fun getParentViewGroup(): ViewGroup? = decorViewGroup()

}