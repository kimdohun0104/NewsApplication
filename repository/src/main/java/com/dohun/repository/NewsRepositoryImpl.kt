package com.dohun.repository

import com.dohun.local.dataSource.LocalNewsDataSource
import com.dohun.model.NewsModel
import com.dohun.model.repository.NewsRepository
import com.dohun.remote.dataSource.RemoteNewsDataSource

class NewsRepositoryImpl(
    private val localNewsDataSource: LocalNewsDataSource,
    private val remoteNewsDataSource: RemoteNewsDataSource
) : NewsRepository {
    override suspend fun getNewsList(): List<NewsModel> = withCon
    }

}