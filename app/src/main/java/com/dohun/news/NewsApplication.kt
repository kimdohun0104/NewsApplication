package com.dohun.news

import android.app.Application
import com.dohun.local.di.localModule
import com.dohun.news.di.appModule
import com.dohun.remote.di.remoteModule
import com.dohun.repository.di.repositoryModule
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