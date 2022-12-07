package com.codependent.correlation.kafka.producer

import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.time.OffsetDateTime


/**
 * @author José A. Íñigo
 */
@Component
class EventProducer(private val streamBridge: StreamBridge) {

    private val logger = LoggerFactory.getLogger(javaClass)

    fun send(eventKey: String, eventValue: String) {

        val message = MessageBuilder.withPayload(eventValue)
            .setHeader(KafkaHeaders.KEY, eventKey)
            .setHeader(KafkaHeaders.TIMESTAMP, OffsetDateTime.now().toInstant().toEpochMilli())
            .build()

        logger.info("Sending message to kafka queue {}", message)
        streamBridge.send("event-out-0", message)
    }

}
