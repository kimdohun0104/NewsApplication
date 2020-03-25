package com.dohun.remote

import com.dohun.remote.news.JsoupMetadataCrawler
import com.dohun.remote.news.MetadataCrawler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MetadataCrawlerTests {

    private lateinit var crawler: MetadataCrawler

    @Before
    fun init() {
        crawler = JsoupMetadataCrawler()
    }

    @Test
    fun `테스트 URL1`() {
        val meta = crawler.crawlMetadata("http://www.hani.co.kr/arti/area/honam/934119.html")

        assertEquals(
            "http://flexible.img.hani.co.kr/flexible/normal/970/646/imgdb/original/2020/0325/20200325502216.jpg",
            meta.image
        )
        assertEquals(
            "“뉴스 출연시켜주겠다” 속여 활동비 챙겨 윤 시장은 ‘공직선거법 위반’ 징역형 확정",
            meta.description
        )
    }

    @Test
    fun `테스트 URL2`() {
        val meta = crawler.crawlMetadata("http://www.hani.co.kr/arti/area/capital/934111.html")

        assertEquals(
            "http://flexible.img.hani.co.kr/flexible/normal/970/582/imgdb/child/2020/0325/53_15851147069261_20200325501902.jpg",
            meta.image
        )
        assertEquals(
            "여주시처럼 기본소득 확대시 재정 지원 검토하기로여주시민은 재난기본소득 1인당 모두 20만원씩 받아",
            meta.description
        )
    }

    @Test
    fun `테스트 URL3`() {
        val meta = crawler.crawlMetadata("https://www.youtube.com/watch?v=YSpEhNlvjxg")

        assertEquals(
            "https://i.ytimg.com/vi/YSpEhNlvjxg/hqdefault.jpg",
            meta.image
        )
        assertEquals(
            "학교 앞 어린이 교통안전을 대폭 강화하는 이른바 '민식이법'이 오늘(25일)부터 시행됩니다. 정부는 올해 2천60억 원을 들여 어린이 보호구역에 무인 단속장비와 신호등을 각각 2천여 대를 우선 설치할 방침입니다. 또 학교와 유치원 근처 불법 노상주차장을 모두 폐지하고, 운전자가 어...",
            meta.description
        )
    }

    @Test
    fun `ogimage ogdescription이 없는 URL 테스트"`() {
        val meta = crawler.crawlMetadata("http://health.chosun.com/site/data/html_dir/2020/03/25/2020032501967.html")

        assertEquals(null, meta.image)
        assertEquals(null, meta.description)
    }
}