package com.example.logsystem.service

import com.example.logsystem.entity.LogDocument
import org.elasticsearch.common.unit.Fuzziness
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
class LogDocumentService(private val elasticsearchTemplate: ElasticsearchRestTemplate) {

    fun search(
        serviceName: String?,
        startDate: LocalDateTime?,
        endDate: LocalDateTime?,
        level: String?,
        traceId: String?,
        className: String?,
        message: String?,
        pageNumber: Int,
        pageSize: Int,
        sortOrder: String
    ): Page<LogDocument> {
        // 设置分页参数
        val pageable = PageRequest.of(pageNumber, pageSize)
        val qb = BoolQueryBuilder()

        // 添加服务名称查询条件
        if (!serviceName.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("serviceName", serviceName))
        }

        // 添加时间范围查询条件
        if (startDate != null || endDate != null) {
            val rangeQuery = QueryBuilders.rangeQuery("@timestamp")
            startDate?.let { rangeQuery.gte(it) }
            endDate?.let { rangeQuery.lte(it) }
            qb.must(rangeQuery)
        }

        // 添加日志级别查询条件
        if (!level.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("logLevel", level))
        }

        // 添加 Trace ID 查询条件
        if (!traceId.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("traceId", traceId))
        }

        // 添加类名查询条件
        if (!className.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("className", className).fuzziness(Fuzziness.AUTO))
        }

        // 添加消息内容查询条件
        if (!message.isNullOrBlank()) {
            qb.must(QueryBuilders.matchQuery("message", message).fuzziness(Fuzziness.AUTO))
        }

        // 确定排序方式，默认升序
        val sort = SortBuilders.fieldSort("@timestamp")
            .order(if (sortOrder.equals("desc", ignoreCase = true)) SortOrder.DESC else SortOrder.ASC)

        // 构建查询对象
        val query = NativeSearchQueryBuilder()
            .withQuery(qb)
            .withPageable(pageable)
            .withSort(sort) // 动态设置排序参数
            .build()

        // 执行查询
        val searchHits: SearchHits<LogDocument> = elasticsearchTemplate.search(query, LogDocument::class.java)

        // 将查询结果转换为分页对象
        val searchHitsList: List<SearchHit<LogDocument>> = searchHits.searchHits.toList()

        return PageImpl(searchHitsList.map { it.content }, pageable, searchHits.totalHits)
    }
}
