package com.dohun.remote

import com.dohun.remote.dataSource.RemoteNewsDataSource
import com.dohun.remote.dataSource.RemoteNewsDataSourceImpl
import com.dohun.remote.dummy.CrawledMetadataDummy
import com.dohun.remote.dummy.NewsResponseDummy
import com.dohun.remote.news.MetadataCrawler
import com.dohun.remote.news.NewsParser
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class RemoteNewsDataSourceTests {

    @Mock
    private lateinit var newsParser: NewsParser

    @Mock
    private lateinit var metadataCrawler: MetadataCrawler

    private lateinit var dataSource: RemoteNewsDataSource

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        dataSource = RemoteNewsDataSourceImpl(newsParser, metadataCrawler)
    }

    @Test
    fun `getNewsList 성공 테스트`() = runBlocking {
        `when`(newsParser.parse()).thenReturn(NewsResponseDummy.beforeMetadata)

        val newsList = newsParser.parse()
        newsList.forEachIndexed { index, news ->
            `when`(metadataCrawler.crawlMetadata(news.link ?: ""))
                .thenReturn(CrawledMetadataDummy.getMetadataByIndex(index))
        }

        assertEquals(dataSource.getNewsList(), NewsResponseDummy.afterMetadata)
    }

    @Test
    fun `특정 인덱스 메타 데이터 크롤링 Exception 테스트`() = runBlocking {
        `when`(newsParser.parse()).thenReturn(NewsResponseDummy.beforeMetadata)
        val newsList = newsParser.parse()
        newsList.forEachIndexed { index, news ->
            if (index == 0) {
                `when`(metadataCrawler.crawlMetadata(news.link ?: "")).thenThrow(RuntimeException())
            } else {
                `when`(metadataCrawler.crawlMetadata(news.link ?: ""))
                    .thenReturn(CrawledMetadataDummy.getMetadataByIndex(index))
            }
        }

        dataSource.getNewsList()

        assertEquals(newsList[0].description, null)
        assertEquals(newsList[0].thumbnail, null)
    }
}