package com.joel.articlesystem.article.repository

import com.joel.articlesystem.article.domain.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleRepository: JpaRepository<ArticleEntity, Int> {
}