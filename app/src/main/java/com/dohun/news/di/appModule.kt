package com.dohun.news.di

import com.dohun.news.ui.newsList.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel { NewsListViewModel(get()) }
}