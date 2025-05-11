package com.joel.articlesystem.article.domain

data class ArticleDTO(
        val id: Int?,
        val source: String?,
        val title: String?,
        val abstracts: String?,
        val journalTitle: String?,
        val journalPublisher: String?
)
