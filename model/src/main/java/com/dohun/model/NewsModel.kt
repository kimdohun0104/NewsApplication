package com.dohun.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NewsModel(

    val guid: String,

    val title: String,

    val link: String,

    val thumbnail: String?,

    val description: String?,

    val tags: List<String>?
) : Parcelable