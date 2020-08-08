package com.dharyiswara.sehatq.helper.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dharyiswara.sehatq.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), BaseView {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            true -> requireActivity().window.decorView as ViewGroup
            else -> null
        }
    }

    private fun isDecorViewGroup(): Boolean {
        return requireActivity().window.decorView is ViewGroup
    }

    private fun getParentViewGroup(): ViewGroup? = decorViewGroup()

}