package com.dharyiswara.sehatq.ui.search

import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.helper.base.BaseActivity
import com.dharyiswara.sehatq.helper.extension.gone
import com.dharyiswara.sehatq.helper.extension.hideKeyboard
import com.dharyiswara.sehatq.helper.extension.visible
import com.dharyiswara.sehatq.model.ProductPromo
import com.dharyiswara.sehatq.ui.detail.DetailProductActivity
import com.dharyiswara.sehatq.ui.history.adapter.ProductAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity

class SearchActivity : BaseActivity() {

    private val productAdapter by lazy {
        ProductAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    private var productList = listOf<ProductPromo>()

    companion object {

        const val LIST_DATA = "list_data"

    }

    override fun getLayoutResId(): Int = R.layout.activity_search

    override fun initView() {
        super.initView()

        productList = intent.getParcelableArrayListExtra(LIST_DATA)
        with(rvSearch) {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = productAdapter
        }
    }

    override fun initEvent() {
        super.initEvent()

        ivBack.setOnClickListener {
            onBackPressed()
        }
        etSearch?.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                v.hideKeyboard()
                v.clearFocus()
            }
            return@setOnEditorActionListener false
        }

        etSearch?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    if (it.isEmpty()) {
                        tvEmptySearch.gone()
                        productAdapter.clearData()
                    } else {
                        val list = mutableListOf<ProductPromo>()
                        productList.forEach { product ->
                            if (product.title.contains(it.toString(), true))
                                list.add(product)
                        }

                        if (list.isNotEmpty()) {
                            tvEmptySearch.gone()
                            productAdapter.replaceData(list)
                        } else {
                            productAdapter.clearData()
                            tvEmptySearch.visible()
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

}