package com.dohun.news.ui.binding

import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import androidx.core.view.children
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.dohun.model.NewsModel
import com.dohun.news.R
import com.dohun.news.ui.adapter.NewsListAdapter
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup

@BindingAdapter("newsList")
internal fun RecyclerView.bindNewsList(newsListLiveData: LiveData<List<NewsModel>>) {
    newsListLiveData.value?.let { newsList ->
        (this.adapter as NewsListAdapter).submitList(newsList)
    }
}

@BindingAdapter("imageUrlWithHolder")
internal fun ImageView.bindImageUrlWithHolder(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .transform(CenterCrop(), RoundedCorners(24))
        .format(DecodeFormat.PREFER_RGB_565)
        .into(this)
}

@BindingAdapter("imageUrl")
internal fun ImageView.bindImageUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .into(this)
}

@BindingAdapter("newsLink")
internal fun WebView.bindNewsLink(link: String?) {
    link?.let {
        loadUrl(link)
    }
}

@BindingAdapter("tags")
internal fun ChipGroup.bindTags(tags: List<String>?) {
    children.forEachIndexed { index, view ->
        if (tags == null || tags.size <= index) {
            view.visibility = View.GONE
        } else {
            view.visibility = View.VISIBLE
            (view as Chip).text = tags[index]
        }
    }
}