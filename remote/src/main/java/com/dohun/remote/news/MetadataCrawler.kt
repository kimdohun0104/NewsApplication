package com.dohun.remote.news

import org.jsoup.Jsoup
import java.net.URL

data class CrawledMetadata(

    var description: String? = null,
    var image: String? = null
)

interface MetadataCrawler {

    @Throws(Exception::class)
    fun crawlMetadata(link: String): CrawledMetadata
}

class JsoupMetadataCrawler : MetadataCrawler {

    override fun crawlMetadata(link: String): CrawledMetadata {
        val metadata = CrawledMetadata()

        val doc = Jsoup.parse(URL(link), 10000)
        metadata.image = doc.select("meta[property=og:image]").first()?.attr("content")
        metadata.description = doc.select("meta[property=og:description]").first()?.attr("content")

        return metadata
    }
}