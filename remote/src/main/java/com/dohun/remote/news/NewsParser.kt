package com.dohun.remote.news

import android.util.Xml
import com.dohun.remote.response.NewsResponse
import org.xmlpull.v1.XmlPullParser
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

interface NewsParser {

    @Throws(IOException::class)
    fun parse(): List<NewsResponse>
}


class NewsParserImpl : NewsParser {

    companion object {
        private const val NEWS_URL = "https://news.google.com/rss?hl=ko&gl=KR&ceid=KR:ko"
    }

    override fun parse(): List<NewsResponse> {
        val inputStream = fetchXml(NEWS_URL)

        val parser = Xml.newPullParser()
        parser.setInput(inputStream, null)
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false)

        return readNewsList(parser)
    }

    private fun fetchXml(url: String): InputStream? =
        (URL(url).openConnection() as? HttpURLConnection)?.run {
            readTimeout = 10000
            connectTimeout = 10000
            connect()
            inputStream
        }

    private fun readNewsList(parser: XmlPullParser): List<NewsResponse> {
        val newsList = mutableListOf<NewsResponse>()

        while (parser.next() != XmlPullParser.END_DOCUMENT) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            when (parser.name) {
                "item" -> newsList.add(readItem(parser))
                "rss", "channel" -> parser.next()
                else -> skipCurrentTag(parser)
            }
        }

        return newsList
    }

    private fun readItem(parser: XmlPullParser): NewsResponse {
        val news = NewsResponse()
        while (parser.next() != XmlPullParser.END_TAG) {
            if (parser.eventType != XmlPullParser.START_TAG) {
                continue
            }

            when (parser.name) {
                "title" -> news.title = parser.nextText()
                "guid" -> news.guid = parser.nextText()
                "link" -> news.link = parser.nextText()
            }
        }
        return news
    }

    private fun skipCurrentTag(parser: XmlPullParser) {
        var depth = 1
        while (depth != 0) {
            when (parser.next()) {
                XmlPullParser.END_TAG -> depth--
                XmlPullParser.START_TAG -> depth++
            }
        }
    }
}