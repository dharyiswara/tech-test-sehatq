package com.dharyiswara.sehatq.ui.history

import androidx.recyclerview.widget.LinearLayoutManager
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.database.ProductRealm
import com.dharyiswara.sehatq.helper.base.BaseActivity
import com.dharyiswara.sehatq.helper.extension.gone
import com.dharyiswara.sehatq.helper.extension.visible
import com.dharyiswara.sehatq.ui.detail.DetailProductActivity
import com.dharyiswara.sehatq.ui.history.adapter.ProductAdapter
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_purchase_history.*
import org.jetbrains.anko.startActivity

class PurchaseHistoryActivity : BaseActivity() {

    private val productRealm by lazy { ProductRealm(Realm.getDefaultInstance()) }

    private val productAdapter by lazy {
        ProductAdapter {
            startActivity<DetailProductActivity>(
                DetailProductActivity.PRODUCT to it
            )
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_purchase_history

    override fun initView() {
        super.initView()

        val product = productRealm.getProductList()
        with(rvHistory) {
            layoutManager = LinearLayoutManager(this@PurchaseHistoryActivity)
            adapter = productAdapter
        }

        if (product.isNotEmpty()) {
            tvEmptyPurchased.gone()
            productAdapter.addData(product)
        } else {
            tvEmptyPurchased.visible()
        }
    }

    override fun initEvent() {
        super.initEvent()

        ivBack.setOnClickListener {
            onBackPressed()
        }
    }

}