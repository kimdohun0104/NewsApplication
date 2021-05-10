package com.dohun.news.common

import android.webkit.WebView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

/**
 * NewsDetail
 */
@BindingAdapter("imageUrl")
fun ImageView.bindImageUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("linkUrl")
internal fun WebView.bindLinkUrl(link: String?) {
    link?.let { loadUrl(link) }
}

@BindingAdapter("tags")
internal fun ChipGroup.bindTags(tags: List<String>?) {
    removeAllViews()
    tags?.let {
        it.forEach { tag ->
            addView(
                Chip(context).apply {
                    text = tag
                }
            )
        }
    }
}