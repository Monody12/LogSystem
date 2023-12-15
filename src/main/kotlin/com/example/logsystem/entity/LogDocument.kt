package com.example.logsystem.entity

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

/**
 * Logstash日志
 * 格式示例：
 *       {
 *         "_index" : "logstash-index",
 *         "_type" : "_doc",
 *         "_id" : "bScPF4oB9pvw7coGFaxX",
 *         "_score" : 1.0,
 *         "_source" : {
 *           "@version" : "1",
 *           "serviceName" : "server-manager",
 *           "host" : "103.177.44.43",
 *           "thread" : "restartedMain",
 *           "pid" : "23619",
 *           "class" : "org.apache.catalina.core.StandardEngine",
 *           "logLevel" : "INFO",
 *           "stackTrace" : "",
 *           "traceId" : "",
 *           "port" : 38601,
 *           "message" : "Starting Servlet engine: [Apache Tomcat/9.0.75]",
 *           "@timestamp" : "2023-08-21T07:46:18.719Z"
 *         }
 */
@Document(indexName = "logstash-index", shards = 1, replicas = 1)
data class LogDocument(
    @Id
    val id: String? = null,
    @Field(name = "@version", type = FieldType.Keyword)
    val version: String? = null,
    @Field(name = "class",type = FieldType.Text, analyzer = "ik_max_word")
    val class_: String? = null,
    @Field(type = FieldType.Keyword)
    val pid: String? = null,
    @Field(type=FieldType.Integer)
    val port: Int? = null,
    @Field(type = FieldType.Keyword)
    val serviceName: String? = null,
    @Field(type = FieldType.Keyword)
    val host: String? = null,
    @Field(name = "@timestamp", type = FieldType.Date, format = [DateFormat.date_time])
    val timestamp: LocalDateTime? = null,
    @Field(type = FieldType.Keyword)
    val thread: String? = null,
    @Field(type = FieldType.Keyword)
    val logLevel: String? = null,
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    val message:String?=null,
    @Field(type = FieldType.Keyword)
    val traceId:String?=null,
)