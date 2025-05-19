package com.joel.articlesystem.config

import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class SolrConfig(
    @Value("\${solr.url}")
    private var solrUrl: String? = null
) {

    @Bean
    @Qualifier("documentPostSolrClient")
    fun documentPostSolrClient(): HttpSolrClient {
        return HttpSolrClient.Builder("$solrUrl/$DOCUMENT_CORE_ALIAS")
            .withConnectionTimeout(10, TimeUnit.SECONDS)
            .build()
    }

    companion object {
        const val DOCUMENT_CORE_ALIAS: String = "kdiscovery_article"
    }
}