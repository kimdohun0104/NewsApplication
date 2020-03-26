package com.dohun.news.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dohun.model.NewsModel
import com.dohun.news.R
import com.dohun.news.ui.adapter.NewsListAdapter

@BindingAdapter("newsList")
internal fun RecyclerView.bindNewsList(newsListLiveData: LiveData<List<NewsModel>>) {
    newsListLiveData.value?.let { newsList ->
        (this.adapter as NewsListAdapter).submitList(newsList)
    }
}

@BindingAdapter("imageUrl")
internal fun ImageView.bindImageUrl(imageUrl: String?) {
    Glide.with(context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .into(this)
}