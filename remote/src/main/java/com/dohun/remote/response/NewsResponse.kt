package com.dohun.remote.response

data class NewsResponse(
    var guid: String? = null,
    var title: String? = null,
    var link: String? = null,
    var thumbnail: String? = null,
    var description: String? = null,
    var tags: List<String>? = null
)