package com.dharyiswara.sehatq.helper

import android.os.Bundle
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.helper.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_blank.*

class BlankFragment : BaseFragment() {

    companion object {

        const val TITLE = "title"

        fun newInstance(title: String): BlankFragment {
            val bundle = Bundle()
            bundle.putString(TITLE, title)
            val fragment = BlankFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    override fun getLayoutResId(): Int = R.layout.fragment_blank

    override fun initView() {
        super.initView()
        tvFragment.text = arguments?.getString(TITLE) ?: TextUtils.BLANK
    }

}