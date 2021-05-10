package com.dohun.repository

import com.dohun.news.local.dataSource.LocalNewsDataSource
import com.dohun.news.model.Result.Failure
import com.dohun.news.model.Result.Success
import com.dohun.news.model.repository.NewsRepository
import com.dohun.news.repository.NewsRepositoryImpl
import com.dohun.news.remote.dataSource.RemoteNewsDataSource
import com.dohun.repository.dummy.NewsEntityDummy
import com.dohun.repository.dummy.NewsResponseDummy
import com.dohun.news.mapper.toEntity
import com.dohun.news.mapper.toModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class NewsRepositoryTests {

    @Mock
    private lateinit var localDataSource: LocalNewsDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteNewsDataSource

    private lateinit var repository: NewsRepository

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        repository = NewsRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Test
    fun `원격 뉴스 리스트 성공 테스트`() = runBlocking {
        `when`(remoteDataSource.getNewsList()).thenReturn(NewsResponseDummy.responses)
        `when`(localDataSource.deleteNewsList()).thenReturn(Unit)
        `when`(localDataSource.insertNewsList(remoteDataSource.getNewsList().map { it.toEntity() })).thenReturn(Unit)

        val newsList = repository.getNewsList()
        verify(localDataSource).deleteNewsList()
        verify(localDataSource).insertNewsList(remoteDataSource.getNewsList().map { it.toEntity() })
        verifyNoMoreInteractions(localDataSource)

        assertTrue(newsList is Success)
        assertEquals((newsList as Success).data, NewsResponseDummy.responses.map { it.toModel() })
        assertFalse(newsList.isLocal)
    }

    @Test
    fun `캐싱된 뉴스 리스트 반환 테스트`() = runBlocking {
        `when`(remoteDataSource.getNewsList()).thenThrow(RuntimeException())
        `when`(localDataSource.getNewsList()).thenReturn(NewsEntityDummy.entities)

        val newsList = repository.getNewsList()
        verify(remoteDataSource).getNewsList()
        verify(localDataSource).getNewsList()
        verifyNoMoreInteractions(remoteDataSource)
        verifyNoMoreInteractions(localDataSource)

        assertTrue(newsList is Success)
        assertEquals((newsList as Success).data, NewsEntityDummy.entities.map { it.toModel() })
        assertTrue(newsList.isLocal)
    }

    @Test
    fun `캐싱된 리스트가 비었을 때 테스트`() = runBlocking {
        val exception = RuntimeException()
        `when`(remoteDataSource.getNewsList()).thenThrow(exception)
        `when`(localDataSource.getNewsList()).thenReturn(emptyList())

        val result = repository.getNewsList()
        verify(remoteDataSource).getNewsList()
        verify(localDataSource).getNewsList()
        verifyNoMoreInteractions(remoteDataSource)
        verifyNoMoreInteractions(localDataSource)

        assertTrue(result is Failure)
        assertEquals((result as Failure).exception, exception)
    }
}