package com.joel.articlesystem.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.batch.core.launch.support.TaskExecutorJobLauncher
import org.springframework.batch.core.repository.JobRepository
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {
    @Primary
    @Bean
    fun dataSource(
            @Value("\${spring.datasource.url}") url: String,
            @Value("\${spring.datasource.username}") username: String,
            @Value("\${spring.datasource.password}") password: String
    ): DataSource {
        val config = HikariConfig().apply {
            jdbcUrl = url
            this.username = username
            this.password = password
            maximumPoolSize = 10
            minimumIdle = 5
            idleTimeout = 30000
            connectionTimeout = 20000
            isRegisterMbeans = false
            isAutoCommit = false
        }
        return HikariDataSource(config)
    }

    @Bean(name = ["subDatasource"])
    fun subDatasource(): DataSource {
        val embeddedDatabaseBuilder = EmbeddedDatabaseBuilder()
        return embeddedDatabaseBuilder.setType(EmbeddedDatabaseType.H2)
            .addScript("/org/springframework/batch/core/schema-h2.sql")
            .build()
    }

    @Primary
    @Bean
    fun jobRepository(): JobRepository {
        val factory = JobRepositoryFactoryBean()
        factory.setDataSource(subDatasource())
        factory.transactionManager = transactionManager()
        factory.afterPropertiesSet()
        return factory.getObject()
    }

    @Bean
    fun transactionManager(): PlatformTransactionManager {
        return JpaTransactionManager()
    }

    @Bean
    fun jobLauncher(): JobLauncher {
        val jobLauncher = TaskExecutorJobLauncher()
        jobLauncher.setJobRepository(jobRepository())
        jobLauncher.afterPropertiesSet()
        return jobLauncher
    }
}