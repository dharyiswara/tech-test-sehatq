package com.dharyiswara.sehatq.ui.main.home

import android.view.MotionEvent
import androidx.lifecycle.Observer
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.helper.base.BaseFragment
import com.dharyiswara.sehatq.helper.extension.gone
import com.dharyiswara.sehatq.helper.extension.visible
import com.dharyiswara.sehatq.model.Homepage
import com.dharyiswara.sehatq.ui.detail.DetailProductActivity
import com.dharyiswara.sehatq.ui.main.home.adapter.CategoryAdapter
import com.dharyiswara.sehatq.ui.main.home.adapter.ProductHomeAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject

class HomeFragment : BaseFragment() {

    private val viewModel by inject<HomeViewModel>()

    private val categoryAdapter by lazy { CategoryAdapter() }

    private val productAdapter by lazy {
        ProductHomeAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun getLayoutResId(): Int = R.layout.fragment_home

    override fun initView() {
        super.initView()
        with(rvCategory) {
            layoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(
                    requireContext(),
                    androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL,
                    false
                )
            adapter = categoryAdapter
        }
        with(rvProduct) {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(requireContext())
            adapter = productAdapter
        }
        categoryAdapter.clearData()
        productAdapter.clearData()
    }

    override fun initEvent() {
        super.initEvent()
        ivListLoved.setOnClickListener {
            toast("List yg disukai")
        }
        etSearch.setOnTouchListener { _, event ->
            if (event?.action == MotionEvent.ACTION_DOWN) {
                if (!swHome.isRefreshing)
                    toast("Go To Search")
            }
            false
        }

        swHome.setOnRefreshListener {
            loadingData(true)
        }
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        if (isFromSwipe) {
            rvCategory.gone()
            rvProduct.gone()
            categoryAdapter.clearData()
            productAdapter.clearData()
        }
        viewModel.getHomepage(isFromSwipe)
    }

    override fun observeData() {
        super.observeData()
        viewModel.homepageData.observe(this, Observer {
            parseObserveData(it,
                resultSuccess = { result ->
                    getHomepageSuccess(result[0].homepage)
                })
        })
    }

    override fun startLoading() {
        swHome.isRefreshing = true
    }

    override fun stopLoading() {
        swHome.isRefreshing = false
    }

    private fun getHomepageSuccess(data: Homepage) {
        if (data.category.isNotEmpty()) {
            rvCategory.visible()
            categoryAdapter.replaceData(data.category)
        }
        if (data.productPromo.isNotEmpty()) {
            rvProduct.visible()
            productAdapter.replaceData(data.productPromo)
        }
    }

}