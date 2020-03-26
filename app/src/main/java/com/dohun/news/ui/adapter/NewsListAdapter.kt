package com.dohun.news.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dohun.model.NewsModel
import com.dohun.news.databinding.ItemNewsBinding

class NewsListAdapter : ListAdapter<NewsModel, NewsListAdapter.NewsViewHolder>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewsModel>() {
            override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean =
                oldItem.guid == newItem.guid

            override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean =
                newItem.title == oldItem.title && newItem.link == oldItem.link
                        && newItem.thumbnail == oldItem.thumbnail
                        && newItem.description == oldItem.description
                        && newItem.tags == oldItem.tags
        }
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.news = getItem(adapterPosition)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bind()
}