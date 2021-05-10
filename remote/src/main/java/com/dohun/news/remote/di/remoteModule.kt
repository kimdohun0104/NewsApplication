package com.dohun.news.remote.di

import com.dohun.news.remote.dataSource.RemoteNewsDataSource
import com.dohun.news.remote.dataSource.RemoteNewsDataSourceImpl
import com.dohun.news.remote.news.*
import org.koin.dsl.module

val remoteModule = module {

    factory<MetadataCrawler> { JsoupMetadataCrawler() }

    factory<NewsParser> { NewsParserImpl() }

    factory<TagExtractor> { TagExtractorImpl() }

    factory<RemoteNewsDataSource> { RemoteNewsDataSourceImpl(get(), get(), get()) }
}