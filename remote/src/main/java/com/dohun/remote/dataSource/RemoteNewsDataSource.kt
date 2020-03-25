package com.dohun.remote.dataSource

import com.dohun.remote.response.NewsResponse

interface RemoteNewsDataSource {

    suspend fun getNewsList(): List<NewsResponse>
}