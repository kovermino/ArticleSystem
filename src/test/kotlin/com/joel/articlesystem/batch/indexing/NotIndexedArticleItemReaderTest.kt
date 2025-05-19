package com.joel.articlesystem.batch.indexing

import com.joel.articlesystem.article.repository.rdb.entity.ArticleEntity
import com.joel.articlesystem.article.repository.rdb.JpaArticleRepository
import com.querydsl.jpa.impl.JPAQueryFactory
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import jakarta.persistence.EntityManager
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.ZonedDateTime

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NotIndexedArticleItemReaderTest : FunSpec() {
    @Autowired
    private lateinit var jpaArticleRepository: JpaArticleRepository

    @Autowired
    private lateinit var entityManager: EntityManager

    private lateinit var queryFactory: JPAQueryFactory

    private lateinit var sut: NotIndexedArticleItemReader

    init {
        extensions(SpringExtension)

        beforeTest {
            jpaArticleRepository.saveAll(
                listOf(
                    ArticleEntity(id = 1, title = "Article 1", source = "Content 1", abstracts = "", journalEntity = null, indexedAt = null),
                    ArticleEntity(id = 2, title = "Article 2", source = "Content 2", abstracts = "", journalEntity = null, indexedAt = null),
                    ArticleEntity(id = 3, title = "Article 3", source = "Content 3", abstracts = "", journalEntity = null, indexedAt = ZonedDateTime.now()),
                    ArticleEntity(id = 4, title = "Article 4", source = "Content 4", abstracts = "", journalEntity = null, indexedAt = null)
                )
            )

            queryFactory = JPAQueryFactory(entityManager)
            sut = NotIndexedArticleItemReader(queryFactory, pageSize = 2)
        }

        test("indexedAt 의 값이 null 인 데이터를 전부 읽는다") {
            val firstArticle = sut.read()
            firstArticle shouldBe 1

            val secondArticle = sut.read()
            secondArticle shouldBe 2

            val thirdArticle = sut.read()
            thirdArticle shouldBe 3

            val forthArticle = sut.read()
            forthArticle shouldBe null
        }
    }
}