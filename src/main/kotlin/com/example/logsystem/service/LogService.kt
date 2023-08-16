package com.example.logsystem.service

import com.example.logsystem.pojo.LogPojo

interface LogService {
    fun selectPage(page: Int, size: Int): List<LogPojo>
}