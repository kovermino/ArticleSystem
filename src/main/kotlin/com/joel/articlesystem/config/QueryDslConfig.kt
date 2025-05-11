package com.joel.articlesystem.config

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.PersistenceContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class QueryDslConfig(
    @PersistenceContext
    private val entityManagerFactory: EntityManagerFactory
) {
    @Bean
    fun jpaQueryFactory(): JPAQueryFactory {
        return JPAQueryFactory(entityManagerFactory.createEntityManager())
    }
}