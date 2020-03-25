package com.dohun.repository

import com.dohun.local.dataSource.LocalNewsDataSource
import com.dohun.model.NewsModel
import com.dohun.model.Result
import com.dohun.model.Result.Failure
import com.dohun.model.Result.Success
import com.dohun.model.repository.NewsRepository
import com.dohun.remote.dataSource.RemoteNewsDataSource
import com.dohun.remote.response.NewsResponse
import com.dohun.repository.mapper.toEntity
import com.dohun.repository.mapper.toModel

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