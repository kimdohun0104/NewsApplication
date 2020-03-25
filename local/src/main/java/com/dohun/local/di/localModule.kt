package com.dohun.local.di

import androidx.room.Room
import com.dohun.local.AppDatabase
import com.dohun.local.dataSource.LocalNewsDataSource
import com.dohun.local.dataSource.LocalNewsDataSourceImpl
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "news.db").build()
    }

    factory { get<AppDatabase>().newsDao() }

    factory<LocalNewsDataSource> { LocalNewsDataSourceImpl(get()) }
}