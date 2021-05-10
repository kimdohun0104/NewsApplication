package com.dohun.news.remote.dataSource

import com.dohun.news.remote.response.NewsResponse

interface RemoteNewsDataSource {

    suspend fun getNewsList(): List<NewsResponse>
}