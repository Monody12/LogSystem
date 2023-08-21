package com.example.logsystem.service

import com.example.logsystem.entity.LogDocument

interface LogService {
    fun selectPage(page: Int, size: Int): List<LogDocument>
}