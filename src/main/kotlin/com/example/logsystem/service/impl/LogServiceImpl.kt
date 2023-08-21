package com.example.logsystem.service.impl

import com.example.logsystem.entity.LogDocument
import com.example.logsystem.service.LogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service

@Service
class LogServiceImpl : LogService {

    @Autowired
    lateinit var elasticsearchRestTemplate: ElasticsearchRestTemplate

    override fun selectPage(page: Int, size: Int): List<LogDocument> {
        val query = NativeSearchQueryBuilder()
            .withPageable(PageRequest.of(page, size))
            .build()
        val search: SearchHits<LogDocument> = elasticsearchRestTemplate.search(query, LogDocument::class.java)
        return search.map { it.content }.toList()
    }
}