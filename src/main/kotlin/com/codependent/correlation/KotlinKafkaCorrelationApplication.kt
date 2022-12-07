package com.codependent.correlation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinKafkaCorrelationApplication

fun main(args: Array<String>) {
    runApplication<KotlinKafkaCorrelationApplication>(*args)
}
