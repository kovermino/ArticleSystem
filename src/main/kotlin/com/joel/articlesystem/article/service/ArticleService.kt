package com.joel.articlesystem.article.service

import com.joel.articlesystem.article.domain.ArticleDTO
import com.joel.articlesystem.article.repository.ArticleRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ArticleService(
        private val articleRepository: ArticleRepository
) {
    @Transactional(readOnly = true)
    fun findArticleById(id: Int): ArticleDTO? {
        val entity =  articleRepository.findByIdOrNull(id)
        return entity?.let {
            ArticleDTO(
                    it.id,
                    it.source,
                    it.title,
                    it.abstracts
            )
        }
    }
}