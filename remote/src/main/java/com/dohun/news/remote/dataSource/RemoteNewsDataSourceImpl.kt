package com.dohun.news.remote.dataSource

import com.dohun.news.remote.news.MetadataCrawler
import com.dohun.news.remote.news.NewsParser
import com.dohun.news.remote.news.TagExtractor
import com.dohun.news.remote.response.NewsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RemoteNewsDataSourceImpl(
    private val newsParser: NewsParser,
    private val metadataCrawler: MetadataCrawler,
    private val tagExtractor: TagExtractor
) : RemoteNewsDataSource {

    override suspend fun getNewsList(): List<NewsResponse> = withContext(Dispatchers.IO) {
        newsParser.parse().apply {
            setCrawledDataToList(this)
        }
    }

    private suspend fun setCrawledDataToList(list: List<NewsResponse>) = coroutineScope {
        list.forEach { news ->
            launch {
                news.link?.let { link ->
                    try {
                        val meta = metadataCrawler.crawlMetadata(link)
                        news.description = meta.description
                            news.thumbnail = meta.image
                        news.tags = tagExtractor.getTags(meta.description ?: "")
                    } catch (e: Exception) {
                    }
                }
            }
        }
    }
}