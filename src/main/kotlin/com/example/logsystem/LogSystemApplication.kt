package com.example.logsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LogSystemApplication

fun main(args: Array<String>) {
    runApplication<LogSystemApplication>(*args)
}
