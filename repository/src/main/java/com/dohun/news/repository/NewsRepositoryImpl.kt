package com.dohun.news.repository

import com.dohun.news.local.dataSource.LocalNewsDataSource
import com.dohun.news.model.NewsModel
import com.dohun.news.model.Result
import com.dohun.news.model.Result.Failure
import com.dohun.news.model.Result.Success
import com.dohun.news.model.repository.NewsRepository
import com.dohun.news.remote.dataSource.RemoteNewsDataSource
import com.dohun.news.remote.response.NewsResponse
import com.dohun.news.mapper.toEntity
import com.dohun.news.mapper.toModel

class NewsRepositoryImpl(
    private val localNewsDataSource: LocalNewsDataSource,
    private val remoteNewsDataSource: RemoteNewsDataSource
) : NewsRepository {

    override suspend fun getNewsList(): Result<List<NewsModel>> =
        try {
            val responses = remoteNewsDataSource.getNewsList()
            refreshLocalNews(responses)
            Success(responses.map { it.toModel() })
        } catch (e: Exception) {
            val cachedData = localNewsDataSource.getNewsList()

            if (cachedData == null || cachedData.isEmpty()) {
                Failure(e)
            } else {
                Success(cachedData.map { it.toModel() }, isLocal = true)
            }
        }

    private suspend fun refreshLocalNews(responses: List<NewsResponse>) {
        localNewsDataSource.deleteNewsList()
        localNewsDataSource.insertNewsList(responses.map { it.toEntity() })
    }

}