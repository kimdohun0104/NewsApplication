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

    private val _isNewsLoading = MutableLiveData<Boolean>()
    val isNewsLoading: LiveData<Boolean> = _isNewsLoading

    init {
        loadNewsList()
    }

    fun loadNewsList() = viewModelScope.launch {
        _isNewsLoading.value = true

        val result = newsRepository.getNewsList()
        if (result is Success) {
            _newsList.value = result.data

            if (result.isLocal) _retrySnackbarEvent.call()
        } else {
            _retrySnackbarEvent.call()
        }

        _isNewsLoading.value = false
    }
}