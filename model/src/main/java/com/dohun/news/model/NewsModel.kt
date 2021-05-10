package com.dohun.news.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsModel(

    val guid: String,

    val title: String,

    val link: String,

    val thumbnail: String?,

    val description: String?,

    val tags: List<String>?
) : Parcelable