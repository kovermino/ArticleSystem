package com.joel.articlesystem.batch.indexing

import org.springframework.batch.core.Job
import org.springframework.batch.core.JobParametersBuilder
import org.springframework.batch.core.launch.JobLauncher
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ArticleIndexingJobLauncher(
    private val jobLauncher: JobLauncher,
    private val indexingJob: Job
) {
    @Bean
    fun runJobOnStartup(): ApplicationRunner {
        return ApplicationRunner {
            val jobParameters = JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis()) // 중복 실행 방지
                .toJobParameters()

            jobLauncher.run(indexingJob, jobParameters)
        }
    }
}