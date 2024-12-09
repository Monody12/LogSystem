package com.example.logsystem.controller

import com.example.logsystem.service.LogDocumentService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDateTime

@Controller
class LogDocumentController(private val logDocumentService: LogDocumentService) {

    @GetMapping("/search")
    fun search(
        @RequestParam(required = false) serviceName: String?,
        @RequestParam(required = false) startDate: LocalDateTime?,
        @RequestParam(required = false) endDate: LocalDateTime?,
        @RequestParam(required = false) level: String?,
        @RequestParam(required = false) traceId: String?,
        @RequestParam(required = false) className: String?,
        @RequestParam(required = false) message: String?,
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "50") pageSize: Int,
        @RequestParam(defaultValue = "asc") sortOrder: String,
        model: Model
    ): String {
        val results = logDocumentService.search(serviceName, startDate, endDate, level, traceId, className, message,
            pageNumber, pageSize, sortOrder)
        model.addAttribute("results", results)
        model.addAttribute("serviceName", serviceName)
        model.addAttribute("startDate", startDate)
        model.addAttribute("endDate", endDate)
        model.addAttribute("level", level)
        model.addAttribute("traceId", traceId)
        model.addAttribute("className", className)
        model.addAttribute("message", message)
        model.addAttribute("pageNumber", pageNumber)
        model.addAttribute("pageSize", pageSize)
        model.addAttribute("sortOrder", sortOrder)
        return "search.jsp"
    }
}