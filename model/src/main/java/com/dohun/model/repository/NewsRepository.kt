package com.dohun.model.repository

import com.dohun.model.NewsModel

interface NewsRepository {

    suspend fun getNewsList(): List<NewsModel>
}