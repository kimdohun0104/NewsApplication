package com.dohun.news.ui.newsList.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dohun.news.model.NewsModel
import com.dohun.news.databinding.ItemNewsBinding
import com.dohun.news.ui.news.NewsActivity

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

    inner class NewsViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val item = getItem(position)
            binding.news = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder =
        NewsViewHolder(ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)).apply {
            val listener = View.OnClickListener {
                val context = it.context
                Intent(context, NewsActivity::class.java).apply {
                    putExtra("news", getItem(adapterPosition))
                    context.startActivity(this)
                }
            }
            binding.root.setOnClickListener(listener)
            binding.cgTag.setOnClickListener(listener)
        }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) = holder.bind(position)
}