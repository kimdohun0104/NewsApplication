package com.dohun.repository.dummy

import com.dohun.news.remote.response.NewsResponse

object NewsResponseDummy {
    val responses = (0..5).map {
        NewsResponse("guid$it", "title$it", "link$it", "image$it", "description$it", null)
    }
}