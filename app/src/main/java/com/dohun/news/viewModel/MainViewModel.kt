package com.dohun.news.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dohun.model.NewsModel
import com.dohun.model.Result.Success
import com.dohun.model.repository.NewsRepository
import com.dohun.news.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MainViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    private val _newsList = MutableLiveData<List<NewsModel>>()
    val newsList: LiveData<List<NewsModel>> = _newsList

    private val _retrySnackbarEvent = SingleLiveEvent<Unit>()
    val retrySnackbarEvent: LiveData<Unit> = _retrySnackbarEvent

    private val _isRefreshing = MutableLiveData<Boolean>()
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _isInitialLoading = MutableLiveData<Boolean>()
    val isInitialLoading: LiveData<Boolean> = _isInitialLoading

    init {
        initialLoad()
    }

    fun refreshNewsList() = viewModelScope.launch {
        _isRefreshing.value = true
        loadNewsList()
        _isRefreshing.value = false
    }

    fun initialLoad() = viewModelScope.launch {
        _isInitialLoading.value = true
        loadNewsList()
        _isInitialLoading.value = false
    }

    private suspend fun loadNewsList() {
        val result = newsRepository.getNewsList()
        if (result is Success) {
            _newsList.value = result.data

            if (result.isLocal) _retrySnackbarEvent.call()
        } else {
            _retrySnackbarEvent.call()
        }
    }
}