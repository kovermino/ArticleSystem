package com.joel.articlesystem.article.repository.rdb

import com.joel.articlesystem.article.repository.rdb.entity.ArticleEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaArticleRepository: JpaRepository<ArticleEntity, Int> {
}