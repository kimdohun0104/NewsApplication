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
        assertEquals(result, listOf("가나다", "안녕하세요"))
    }

    @Test
    fun test3() {
        val test = "안녕하세요 가나 안녕하세요 가나다 가나다"
        val result = tagExtractor.getTags(test)
        assertEquals(result, listOf("가나다", "안녕하세요"))
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
}