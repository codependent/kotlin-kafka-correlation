package com.codependent.correlation.kafka.consumer

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.Message
import java.util.function.Consumer

@Configuration
class KafkaConsumerConfiguration {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun consumeEvents(): Consumer<Message<String>>{
        return Consumer { record ->
            logger.info("consumeEvents() -> \nheaders {}\npayload {}",
                record.headers.map { "\n   ${it.key} - ${it.value}" },
                record.payload
            )
        }
    }
}