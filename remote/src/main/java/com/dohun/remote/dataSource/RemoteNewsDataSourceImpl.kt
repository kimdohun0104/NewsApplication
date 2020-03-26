package com.dohun.remote.dataSource

import com.dohun.remote.news.MetadataCrawler
import com.dohun.remote.news.NewsParser
import com.dohun.remote.response.NewsResponse
import kotlinx.coroutines.*

class RemoteNewsDataSourceImpl(
    private val newsParser: NewsParser,
    private val metadataCrawler: MetadataCrawler
) : RemoteNewsDataSource {

    override suspend fun getNewsList(): List<NewsResponse> = withContext(Dispatchers.IO) {
        newsParser.parse().apply {
            setCrawledDataToList(this)
        }
    }

    private suspend fun setCrawledDataToList(list: List<NewsResponse>) = coroutineScope {
        val threadPool = newFixedThreadPoolContext(6, "crawlingThread")
        list.map { news ->
            async(threadPool) {
                news.link?.let { link ->
                    try {
                        val meta = metadataCrawler.crawlMetadata(link)
                        news.description = meta.description
                        news.thumbnail = meta.image
                    } catch (e: Exception) {
                    }
                }
            }
        }.awaitAll()
    }
}