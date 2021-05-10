package com.dohun.news.mapper

import com.dohun.news.local.entity.NewsEntity
import com.dohun.news.model.NewsModel
import com.dohun.news.remote.response.NewsResponse

internal fun NewsResponse.toEntity() = NewsEntity(
    description = this.description,
    thumbnail = this.thumbnail,
    tags = this.tags,
    guid = this.guid ?: "",
    link = this.link ?: "",
    title = this.title ?: ""
)

internal fun NewsResponse.toModel() = NewsModel(
    description = this.description,
    thumbnail = this.thumbnail,
    guid = this.guid ?: "",
    title = this.title ?: "",
    tags = this.tags,
    link = this.link ?: ""
)

internal fun NewsEntity.toModel() = NewsModel(
    guid = this.guid,
    description = this.description,
    link = this.link,
    tags = this.tags,
    thumbnail = this.thumbnail,
    title = this.title
)