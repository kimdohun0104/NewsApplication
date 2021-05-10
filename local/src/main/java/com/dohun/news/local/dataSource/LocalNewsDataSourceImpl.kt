package com.dohun.news.local.dataSource

import com.dohun.news.local.dao.NewsDao
import com.dohun.news.local.dataSource.LocalNewsDataSource
import com.dohun.news.local.entity.NewsEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalNewsDataSourceImpl(
    private val newsDao: NewsDao
) : LocalNewsDataSource {

    override suspend fun getNewsList(): List<NewsEntity>? = withContext(Dispatchers.IO) {
        newsDao.selectList()
    }

    override suspend fun deleteNewsList() = withContext(Dispatchers.IO) {
        newsDao.deleteList()
    }

    override suspend fun insertNewsList(newsList: List<NewsEntity>) = withContext(Dispatchers.IO) {
        newsDao.insertList(newsList)
    }

}