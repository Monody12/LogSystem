import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.12"
    id("io.spring.dependency-management") version "1.1.2"
    kotlin("jvm") version "1.8.22"
    kotlin("plugin.spring") version "1.8.22"
    war
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-elasticsearch")
    implementation("org.elasticsearch:elasticsearch")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    // JSP
    implementation("org.apache.tomcat.embed:tomcat-embed-jasper")
    implementation("javax.servlet:jstl")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

task("printProjectProperties") {
    doLast {
        project.properties.forEach { (key, value) ->
            println("$key = $value")
        }
    }
}

/**
 * 记录所有源码文件和文件夹的路径
 */
task("getAllSourceFiles") {
    doLast {
        val file = file("sourceList.txt")
        sourceSets.forEach { sourceSet ->
            println("sourceSet: ${sourceSet.name}")
            sourceSet.allSource.forEach { sourceDirectory ->
//                println("sourceDirectory: ${sourceDirectory.path}")
                file.appendText("${sourceDirectory.path}\n")
            }
        }
    }
}.dependsOn("printProjectProperties")