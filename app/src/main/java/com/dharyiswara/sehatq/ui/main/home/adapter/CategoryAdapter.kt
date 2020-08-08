package com.dharyiswara.sehatq.ui.main.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dharyiswara.sehatq.R
import com.dharyiswara.sehatq.helper.extension.loadFromUrl
import com.dharyiswara.sehatq.model.Category
import kotlinx.android.synthetic.main.layout_item_category.view.*

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private val categoryList = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item_category, parent, false)
        )
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(categoryList[position])
    }

    fun replaceData(list: List<Category>) {
        categoryList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        categoryList.clear()
        notifyDataSetChanged()
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(category: Category) {
            with(itemView) {
                ivCategory.loadFromUrl(category.imageUrl)
                tvName.text = category.name
            }

        }

    }

}