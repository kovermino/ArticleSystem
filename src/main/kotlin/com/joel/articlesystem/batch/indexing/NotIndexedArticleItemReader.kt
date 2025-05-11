package com.joel.articlesystem.batch.indexing

import com.joel.articlesystem.article.domain.ArticleEntity
import com.joel.articlesystem.article.domain.QArticleEntity
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.batch.item.ItemReader

class NotIndexedArticleItemReader(
    private val queryFactory: JPAQueryFactory,
    private val pageSize: Int = 1000
) : ItemReader<ArticleEntity> {

    private var lastId: Int = 0;
    private var buffer: Iterator<ArticleEntity> = emptyList<ArticleEntity>().iterator()

    override fun read(): ArticleEntity? {
        if(!buffer.hasNext()) {
            buffer = fetchNextChunk()
            if(!buffer.hasNext()) {
                return null
            }
        }
        return buffer.next()
    }

    private fun fetchNextChunk(): Iterator<ArticleEntity> {
        val qArticle = QArticleEntity.articleEntity

        val articles = queryFactory
            .selectFrom(qArticle)
            .where(
                qArticle.indexedAt.isNull,
                qArticle.id.gt(lastId)
            )
            .orderBy(qArticle.id.asc())
            .limit(pageSize.toLong())
            .fetch()

        if(articles.isNotEmpty()) {
            lastId = articles.last().id!!
        }

        return articles.iterator()
    }
}