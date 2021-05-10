package com.dohun.news.dummy

import com.dohun.news.model.NewsModel

object NewsModelDummy {

    val newsList = (0..5).map {
        NewsModel("guid$it", "title$it", "link$it", "thumbnail$it", "description$it", null)
    }
}