package com.example.logsystem.service.impl

import com.example.logsystem.pojo.LogPojo
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

    override fun selectPage(page: Int, size: Int): List<LogPojo> {
        val query = NativeSearchQueryBuilder()
            .withPageable(PageRequest.of(page, size))
            .build()
        val search: SearchHits<LogPojo> = elasticsearchRestTemplate.search(query, LogPojo::class.java)
        return search.map { it.content }.toList()
    }
}