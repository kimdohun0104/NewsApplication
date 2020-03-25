package com.dohun.remote.di

import com.dohun.remote.dataSource.RemoteNewsDataSource
import com.dohun.remote.dataSource.RemoteNewsDataSourceImpl
import com.dohun.remote.news.JsoupMetadataCrawler
import com.dohun.remote.news.MetadataCrawler
import com.dohun.remote.news.NewsParser
import com.dohun.remote.news.NewsParserImpl
import org.koin.dsl.module

val remoteModule = module {

    factory<MetadataCrawler> { JsoupMetadataCrawler() }

    factory<NewsParser> { NewsParserImpl() }

    factory<RemoteNewsDataSource> { RemoteNewsDataSourceImpl(get(), get()) }
}