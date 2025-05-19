package com.joel.articlesystem.article.service

import com.joel.articlesystem.article.repository.rdb.dbvo.ArticleDBVO
import com.joel.articlesystem.article.domain.ArticleDTO
import com.joel.articlesystem.article.repository.rdb.JpaArticleRepository
import com.joel.articlesystem.article.repository.rdb.QueryDslArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
    private val jpaArticleRepository: JpaArticleRepository,
    private val articleQueryDslArticleRepository: QueryDslArticleRepository
) {
    @Transactional(readOnly = true)
    fun findArticleById(id: Int): ArticleDTO? {
        val entity =  jpaArticleRepository.findByIdOrNull(id)
        return entity?.let {
            ArticleDTO(
                    it.id,
                    it.source,
                    it.title,
                    it.abstracts,
                    it.journalEntity?.title,
                    it.journalEntity?.publisher
            )
        }
    }

    @Transactional(readOnly = true)
    fun findArticleByIdQueryDsl(id: Int): ArticleDBVO? {
        return articleQueryDslArticleRepository.getArticleById(id)
    }
}