package com.example.logsystem.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate

@Configuration
class ElasticsearchConfig(val client: RestHighLevelClient) {

    @Bean
    fun elasticsearchTemplate(): ElasticsearchRestTemplate {
        return ElasticsearchRestTemplate(client)
    }
}