package com.dohun.news.model

sealed class Result<T> {
    data class Success<T>(val data: T, val isLocal: Boolean = false) : Result<T>()

    data class Failure<T>(val exception: Exception): Result<T>()
}