package com.dohun.news.model.repository

import com.dohun.news.model.NewsModel
import com.dohun.news.model.Result

interface NewsRepository {

    suspend fun getNewsList(): Result<List<NewsModel>>
}