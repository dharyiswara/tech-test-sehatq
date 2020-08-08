package com.dharyiswara.sehatq.ui.history.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.helper.extension.loadFromUrl
import com.dharyiswara.sehatq.model.ProductPromo
import kotlinx.android.synthetic.main.layout_item_product.view.*

class ProductAdapter(private val listener: (ProductPromo) -> Unit) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val productList = mutableListOf<ProductPromo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_product, parent, false), listener
        )
    }

    override fun getItemCount(): Int = productList.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(productList[position])
    }

    fun addData(list: List<ProductPromo>) {
        productList.addAll(list)
        notifyDataSetChanged()
    }

    fun replaceData(list: List<ProductPromo>) {
        productList.clear()
        productList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        productList.clear()
        notifyDataSetChanged()
    }

    class ProductViewHolder(view: View, private val listener: (ProductPromo) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bind(product: ProductPromo) {
            with(itemView) {
                ivProduct.loadFromUrl(product.imageUrl)
                tvTitle.text = product.title
                tvPrice.text = product.price
                setOnClickListener {
                    listener.invoke(product)
                }
            }

        }

    }

}