package com.example.logsystem.config

import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    /**
     * 全局日期格式化
     */
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(String::class.java, LocalDateTime::class.java) { source ->
            if (source.isBlank()) {
                null
            } else {
                LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"))
            }
        }
    }
}