package com.dohun.repository.di

import com.dohun.model.repository.NewsRepository
import com.dohun.repository.NewsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(get(), get()) }
}