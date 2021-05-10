package com.dohun.news

import android.app.Application
import com.dohun.news.local.di.localModule
import com.dohun.news.di.appModule
import com.dohun.news.di.repositoryModule
import com.dohun.news.remote.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@NewsApplication)
            modules(
                listOf(
                    remoteModule,
                    localModule,
                    repositoryModule,
                    appModule
                )
            )
        }
    }
}