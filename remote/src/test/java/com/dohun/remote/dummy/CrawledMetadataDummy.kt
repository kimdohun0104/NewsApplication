package com.dohun.remote.dummy

import com.dohun.news.remote.news.CrawledMetadata

object CrawledMetadataDummy {

    fun getMetadataByIndex(index: Int) =
        CrawledMetadata("description$index", "image$index")
}