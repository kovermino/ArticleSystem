package com.joel.articlesystem.batch.indexing

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.batch.core.Job
import org.springframework.batch.core.Step
import org.springframework.batch.core.job.builder.JobBuilder
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.step.builder.StepBuilder
import org.springframework.batch.item.ItemProcessor
import org.springframework.batch.item.ItemReader
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.PlatformTransactionManager

@Configuration
class ArticleIndexingJobConfig(
    val jobRepository: JobRepository,
    val transactionManager: PlatformTransactionManager,
    val jpaQueryFactory: JPAQueryFactory
) {
    @Bean
    fun indexingJob(): Job {
        return JobBuilder("articleIndexing", jobRepository)
            .start(indexingStep())
            .build()
    }
    @Bean
    fun indexingStep(): Step {
        return StepBuilder("indexingStep", jobRepository)
            .chunk<Int, Int>(10000, transactionManager)
            .reader(notIndexedArticleReader())
            .processor(dummyProcessor())
            .writer { items -> println("Read IDs: $items") }
            .build()
    }
    @Bean
    fun dummyProcessor(): ItemProcessor<Int, Int> {
        return ItemProcessor { it } // 필요 시 가공 가능
    }
    @Bean
    fun notIndexedArticleReader(): ItemReader<Int> {
        return NotIndexedArticleItemReader(queryFactory = jpaQueryFactory, pageSize = 10000)
    }
}