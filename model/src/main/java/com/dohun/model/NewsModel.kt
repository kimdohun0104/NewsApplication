package com.dohun.model

data class NewsModel(

    val guid: String,

    val title: String,

    val link: String,

    val thumbnail: String?,

    val description: String?,

    val tags: String?
)