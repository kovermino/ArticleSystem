package com.joel.articlesystem.article.repository.rdb

import com.joel.articlesystem.article.repository.rdb.dbvo.ArticleDBVO
import com.joel.articlesystem.article.repository.rdb.entity.QArticleEntity
import com.joel.articlesystem.journal.domain.QJournalEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class QueryDslArticleRepository(
    private val jpaQueryFactory : JPAQueryFactory
) {
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