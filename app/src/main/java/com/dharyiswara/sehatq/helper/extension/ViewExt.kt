package com.dharyiswara.sehatq.helper.extension

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.dharyiswara.sehatq.R

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun ImageView.loadFromUrl(
    imageUrl: String?,
    placeHolder: Drawable? = ContextCompat.getDrawable(context, R.drawable.ic_no_image)
) {
    val options = RequestOptions()
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .priority(Priority.IMMEDIATE)
    Glide.with(this.context)
        .load(imageUrl)
        .placeholder(placeHolder)
        .error(placeHolder)
        .apply(options)
        .into(this)
}

fun View.hideKeyboard() {
    val imm: InputMethodManager by lazy { this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager }
    imm.hideSoftInputFromWindow(windowToken, 0)
}