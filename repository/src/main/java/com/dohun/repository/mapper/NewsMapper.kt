package com.dohun.repository.mapper

import com.dohun.local.entity.NewsEntity
import com.dohun.model.NewsModel
import com.dohun.remote.response.NewsResponse

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