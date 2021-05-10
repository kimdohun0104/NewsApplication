package com.dohun.news.local.dataSource

import com.dohun.news.local.entity.NewsEntity

interface LocalNewsDataSource {

    suspend fun getNewsList(): List<NewsEntity>?

    suspend fun deleteNewsList()

    suspend fun insertNewsList(newsList: List<NewsEntity>)
}