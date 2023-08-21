package com.example.logsystem.service

import com.example.logsystem.entity.LogDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.annotations.Query
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository
import java.time.LocalDateTime

interface LogDocumentRepository : ElasticsearchRepository<LogDocument, String> {

    override fun findAll(pageable: Pageable): Page<LogDocument>

    @Query("{\"bool\": {\"must\": [{\"range\": {\"timestamp\": {\"gte\": \"?0\",\"lte\": \"?1\",\"format\": \"yyyy-MM-dd HH:mm:ss\"}}}]}}")
    fun findByTimestampBetween(startDate: LocalDateTime, endDate: LocalDateTime, pageable: Pageable): Page<LogDocument>

    @Query("{\"bool\": {\"must\": [{\"match\": {\"logLevel\": \"?0\"}}]}}")
    fun findByLevel(level: String, pageable: Pageable): Page<LogDocument>

    @Query("{\"bool\": {\"must\": [{\"match\": {\"traceId\": \"?0\"}}]}}")
    fun findByTraceId(traceId: String, pageable: Pageable): Page<LogDocument>

    @Query("{\"bool\": {\"must\": [{\"match\": {\"class_\": {\"query\": \"?0\",\"operator\": \"and\"}}}]}}")
    fun findByClassNameContaining(className: String, pageable: Pageable): Page<LogDocument>

    @Query("{\"bool\": {\"must\": [{\"match\": {\"message\": {\"query\": \"?0\",\"operator\": \"and\"}}}]}}")
    fun findByMessageContaining(message: String, pageable: Pageable): Page<LogDocument>
}