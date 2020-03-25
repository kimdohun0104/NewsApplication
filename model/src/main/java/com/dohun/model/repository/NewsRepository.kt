package com.dohun.model.repository

import com.dohun.model.NewsModel
import com.dohun.model.Result

interface NewsRepository {

    suspend fun getNewsList(): Result<List<NewsModel>>
}