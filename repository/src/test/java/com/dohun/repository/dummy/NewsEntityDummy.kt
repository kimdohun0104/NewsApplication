package com.dohun.repository.dummy

import com.dohun.local.entity.NewsEntity

object NewsEntityDummy {

    val entities = (0..5).map {
        NewsEntity("guid$it", "title$it", "link$it", "thumbnail$it", "description$it", "tags$it")
    }
}