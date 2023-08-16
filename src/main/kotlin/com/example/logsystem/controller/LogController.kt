package com.example.logsystem.controller

import com.example.logsystem.pojo.LogPojo
import com.example.logsystem.service.LogService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LogController {
    @Autowired
    lateinit var logService: LogService

    @GetMapping("/log")
    fun selectPage(page: Int, size: Int): List<LogPojo> {
        return logService.selectPage(page, size)
    }

}