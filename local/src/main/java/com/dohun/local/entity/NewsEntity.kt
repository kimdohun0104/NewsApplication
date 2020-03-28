package com.dohun.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class NewsEntity(

    @PrimaryKey
    val guid: String,

    val title: String,

    val link: String,

    val thumbnail: String?,

    val description: String?,

    val tags: List<String>?
)