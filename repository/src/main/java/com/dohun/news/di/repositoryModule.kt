package com.dohun.news.di

import com.dohun.news.model.repository.NewsRepository
import com.dohun.news.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}