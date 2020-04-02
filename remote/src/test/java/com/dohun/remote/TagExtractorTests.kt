package com.dohun.remote

import com.dohun.remote.news.TagExtractor
import com.dohun.remote.news.TagExtractorImpl
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TagExtractorTests {

    private lateinit var tagExtractor: TagExtractor

    @Before
    fun init() {
        tagExtractor = TagExtractorImpl()
    }

    @Test
    fun test1() {
        val test = "fff aaa eee bbb ggg ccc ddd fff aaa aaa"
        val result = tagExtractor.getTags(test)
        assertEquals(result, listOf("aaa", "fff", "bbb"))
    }

    @Test
    fun test2() {
        val test = "가나다 가나다 가나 안녕 안녕하세요"
        val result = tagExtractor.getTags(test)
        assertEquals(result, listOf("가나다", "가나", "안녕"))
    }

    @Test
    fun test3() {
        val test = "안녕하세요 가나 안녕하세요 가나다 가나다 가나"
        val result = tagExtractor.getTags(test)
        assertEquals(result, listOf("가나", "가나다", "안녕하세요"))
    }

    @Test
    fun test4() {
        val test = ""
        val result = tagExtractor.getTags(test)
        assertEquals(result, null)
    }

    @Test
    fun test5() {
        val test = " "
        val result = tagExtractor.getTags(test)
        assertEquals(result, null)
    }

    @Test
    fun test6() {
        val test = "EPL이 돌아본 아시아 선수 '최고의 골'…12골 중 3골이 손흥민, 최송아기자, 스포츠뉴스 (송고시간 2020-03-28 15:04)"
        val result = tagExtractor.getTags(test)
        assertEquals(result, listOf("EPL이", "3골이", "골12골"))
    }

    @Test
    fun test7() {
        val test = "중앙대첵본부 31일 0시 기준"
        val result = tagExtractor.getTags(test)
        println(result)
    }

    @Test
    fun test8() {
        println("안녕하".compareTo("가"))
    }
}