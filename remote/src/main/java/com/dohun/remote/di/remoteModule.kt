package com.dohun.remote.di

import com.dohun.remote.dataSource.RemoteNewsDataSource
import com.dohun.remote.dataSource.RemoteNewsDataSourceImpl
import com.dohun.remote.news.*
import org.koin.dsl.module

val remoteModule = module {

    factory<MetadataCrawler> { JsoupMetadataCrawler() }

    factory<NewsParser> { NewsParserImpl() }

    factory<TagExtractor> { TagExtractorImpl() }

    factory<RemoteNewsDataSource> { RemoteNewsDataSourceImpl(get(), get(), get()) }
}