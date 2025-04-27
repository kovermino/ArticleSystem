package com.joel.articlesystem.article.repository

import com.joel.articlesystem.article.domain.ArticleDBVO
import com.joel.articlesystem.article.domain.QArticleEntity
import com.joel.articlesystem.journal.domain.QJournalEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class QueryDslArticleRepository(
        private val entityManager: EntityManager
) {
    private val jpaQueryFactory = JPAQueryFactory(entityManager)

    fun getArticleById(id: Int): ArticleDBVO? {
        val qArticle = QArticleEntity.articleEntity
        val qJournal = QJournalEntity.journalEntity
        val where = qArticle.id.eq(id)
        return jpaQueryFactory.select(
                Projections.constructor(
                        ArticleDBVO::class.java,
                        qArticle.id,
                        qArticle.title,
                        qJournal.title.`as`("journalTitle")
                )
        ).from(qArticle)
                .join(qArticle.journalEntity, qJournal)
                .where(where)
                .fetchFirst()
    }
}