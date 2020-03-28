package com.dohun.remote.dummy

import com.dohun.remote.response.NewsResponse

object NewsResponseDummy {

    val beforeMetadata = (0..5).map {
        NewsResponse("guid$it", "title$it", "link$it", tags = null)
    }

    val afterMetadata = (0..5).map {
        NewsResponse("guid$it", "title$it", "link$it", "image$it", "description$it", null)
    }
}