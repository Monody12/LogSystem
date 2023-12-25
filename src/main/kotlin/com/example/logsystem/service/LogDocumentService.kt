package com.example.logsystem.service

import com.example.logsystem.entity.LogDocument
import org.elasticsearch.index.query.BoolQueryBuilder
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.sort.SortBuilders
import org.elasticsearch.search.sort.SortOrder
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate
import org.springframework.data.elasticsearch.core.SearchHit
import org.springframework.data.elasticsearch.core.SearchHits
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogDocumentService(private val repository: LogDocumentRepository, private val elasticsearchTemplate: ElasticsearchRestTemplate) {

    fun search(
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        level: String?,
        traceId: String?,
        className: String?,
        message: String?,
        pageNumber: Int,
        pageSize: Int
    ): Page<LogDocument> {
        val pageable = PageRequest.of(pageNumber, pageSize)
        val qb = BoolQueryBuilder()

        if (startDate != null && endDate != null) {
            qb.must(QueryBuilders.rangeQuery("timestamp").gte(startDate).lte(endDate))
        }

        if (!level.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("logLevel", level))
        }

        if (!traceId.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("traceId", traceId))
        }

        if (!className.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("class", className))
        }

        if (!message.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("message", message))
        }

        val query = NativeSearchQueryBuilder()
            .withQuery(qb)
            .withPageable(pageable)
            // 查询结果按时间戳升序排序
            .withSort(SortBuilders.fieldSort("@timestamp").order(SortOrder.ASC))
            .build()

        val searchHits: SearchHits<LogDocument> = elasticsearchTemplate.search(query, LogDocument::class.java)

        val searchHitsList: List<SearchHit<LogDocument>> = searchHits.searchHits.toList()

        return PageImpl<LogDocument>(searchHitsList.map { it.content }, pageable, searchHits.totalHits)
    }
}