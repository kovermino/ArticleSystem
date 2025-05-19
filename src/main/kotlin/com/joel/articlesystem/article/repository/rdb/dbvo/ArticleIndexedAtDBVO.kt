package com.joel.articlesystem.article.repository.rdb.dbvo

import java.time.ZonedDateTime

data class ArticleIndexedAtDBVO(
    var id: Int = 0,
    var indexedAt: ZonedDateTime? = null,
)