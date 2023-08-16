package com.example.logsystem.pojo

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType
import java.time.LocalDateTime

/**
 * Logstash日志
 * 格式示例：
 * {
 *         "_index" : "logstash-2023.08.16",
 *         "_type" : "_doc",
 *         "_id" : "tus7_IkBdVYhesXVNYoU",
 *         "_score" : 1.0657513,
 *         "_source" : {
 *           "@version" : "1",
 *           "class" : "org.apache.catalina.core.StandardEngine",
 *           "pid" : "5017",
 *           "rest" : "Starting Servlet engine: [Apache Tomcat/9.0.75]",
 *           "port" : 37879,
 *           "serviceName" : "springAppName_IS_UNDEFINED",
 *           "host" : "103.177.44.43",
 *           "@timestamp" : "2023-08-16T02:44:45.753Z",
 *           "thread" : "restartedMain",
 *           "logLevel" : "INFO"
 *         }
 *       },
 */
//@Document(indexName = "logstash_*", shards = 1, replicas = 1)
@Document(indexName = "logstash-2023.08.16", shards = 1, replicas = 1)
data class LogPojo(
    @Id
    val id: String? = null,
    @Field(name = "@version", type = FieldType.Keyword)
    val version: String? = null,
    @Field(name = "_class",type = FieldType.Keyword)
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
)