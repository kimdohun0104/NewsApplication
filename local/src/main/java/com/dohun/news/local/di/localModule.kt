package com.dohun.news.local.di

import androidx.room.Room
import com.dohun.news.local.AppDatabase
import com.dohun.news.local.dataSource.LocalNewsDataSource
import com.dohun.news.local.dataSource.LocalNewsDataSourceImpl
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "news.db").build()
    }

    single { get<AppDatabase>().newsDao() }

    factory<LocalNewsDataSource> { LocalNewsDataSourceImpl(get()) }
}