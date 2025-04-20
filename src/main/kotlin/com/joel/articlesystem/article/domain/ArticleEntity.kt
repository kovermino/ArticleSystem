package com.joel.articlesystem.article.domain

import jakarta.persistence.*

@Entity
@Table(name = "kd_article")
data class ArticleEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "mk")
        val id: Int,
        @Column(name = "content_id")
        val source: String,
        val title: String,
        @Column(name = "abstract")
        val abstracts: String
) {
}