package com.example.logsystem.service

import com.example.logsystem.entity.LogDocument
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class LogDocumentService(private val repository: LogDocumentRepository) {

//    fun search(
//        startDate: LocalDateTime?,
//        endDate: LocalDateTime?,
//        level: String?,
//        traceId: String?,
//        className: String?,
//        message: String?
//    ): List<LogDocument> {
//        var result: List<LogDocument> = mutableListOf()
//
//        if (startDate != null && endDate != null) {
//            result = repository.findByTimestampBetween(startDate, endDate)
//        }
//
//        if (level != null) {
//            result = result.filter { it.logLevel == level }
//        }
//
//        if (traceId != null) {
//            result = result.filter { it.traceId == traceId }
//        }
//
//        if (className != null) {
//            result = result.filter { it.class_?.contains(className) ?: false }
//        }
//
//        if (message != null) {
//            result = result.filter { it.message?.contains(message) ?: false }
//        }
//
//        return result
//    }

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

        var result: Page<LogDocument> = repository.findAll(pageable)

        if (startDate != null && endDate != null) {
            result = repository.findByTimestampBetween(startDate, endDate, pageable)
        }

        if (level != null) {
            result = repository.findByLevel(level, pageable)
        }

        if (traceId != null) {
            result = repository.findByTraceId(traceId, pageable)
        }

        if (className != null) {
            result = repository.findByClassNameContaining(className, pageable)
        }

        if (message != null) {
            result = repository.findByMessageContaining(message, pageable)
        }

        return result
    }
}