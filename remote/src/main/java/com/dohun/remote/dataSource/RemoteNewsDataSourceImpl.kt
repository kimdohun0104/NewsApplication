package com.dohun.remote.dataSource

import com.dohun.remote.news.MetadataCrawler
import com.dohun.remote.news.NewsParser
import com.dohun.remote.news.TagExtractor
import com.dohun.remote.response.NewsResponse
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