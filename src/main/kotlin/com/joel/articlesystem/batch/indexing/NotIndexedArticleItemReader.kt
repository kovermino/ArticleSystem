package com.joel.articlesystem.batch.indexing

import com.joel.articlesystem.article.repository.rdb.dbvo.ArticleIndexedAtDBVO
import com.joel.articlesystem.article.repository.rdb.entity.QArticleEntity
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.batch.item.ItemReader

class NotIndexedArticleItemReader(
    private val queryFactory: JPAQueryFactory,
    private val pageSize: Int = 10000
) : ItemReader<Int> {
    private var currentId: Int = 80000000;
    private var lastId: Int? = 0;
    private var buffer: Iterator<Int> = emptyList<Int>().iterator()

    init {
        val qArticle = QArticleEntity.articleEntity
        lastId = queryFactory
            .select(qArticle.id.max())
            .from(qArticle)
            .fetchOne()
        println("Max mk: $lastId")
    }

    override fun read(): Int? {
        while(!buffer.hasNext()) {
            buffer = fetchNextChunk()
            if(currentId >= lastId!!) {
                return null
            }
        }
        return buffer.next()
    }

    private fun fetchNextChunk(): Iterator<Int> {
        val qArticle = QArticleEntity.articleEntity

        val articlesInRange = queryFactory
            .select(
                Projections.fields(
                    ArticleIndexedAtDBVO::class.java,
                    qArticle.id,
                    qArticle.indexedAt
                )
            )
            .from(qArticle)
            .where(
                qArticle.id.gt(currentId)
            )
            .orderBy(qArticle.id.asc())
            .limit(pageSize.toLong())
            .fetch()

        if(articlesInRange.isNotEmpty()) {
            println("currentId: $currentId")
            currentId = articlesInRange.last().id!!
        }

        val notIndexedArticles = articlesInRange.filter { it.indexedAt == null }.map { it.id }
        return notIndexedArticles.iterator()
    }
}