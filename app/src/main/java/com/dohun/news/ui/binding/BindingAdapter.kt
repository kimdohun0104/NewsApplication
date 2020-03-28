package com.dohun.news.ui.binding

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
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
import com.google.android.material.chip.ChipDrawable


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
        .centerCrop()
        .into(this)
}

@BindingAdapter("newsLink")
internal fun WebView.bindNewsLink(link: String?) {
    link?.let {
        loadUrl(link)
    }
}

@BindingAdapter("tags")
internal fun TextView.bindTags(tags: List<String>?) {
    tags?.let {
        val sb = SpannableStringBuilder()
        it.forEach { tag ->
            val span = ImageSpan(ChipDrawable.createFromResource(context, R.xml.chip_tag).apply {
                text = tag
                setBounds(0, 0, intrinsicWidth, intrinsicHeight)
            })

            sb.append(tag)
            val endIndex = sb.length

            sb.setSpan(
                span,
                endIndex - tag.length,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        text = sb
    }
}