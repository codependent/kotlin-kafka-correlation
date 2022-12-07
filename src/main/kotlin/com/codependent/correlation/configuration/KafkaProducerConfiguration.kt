package com.codependent.correlation.configuration

import io.micrometer.observation.ObservationRegistry
import org.springframework.cloud.stream.config.ProducerMessageHandlerCustomizer
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler

@Configuration
class KafkaProducerConfiguration {

    @Bean
    fun kafkaProducerObservationCustomizer (applicationContext: ApplicationContext, observationRegistry: ObservationRegistry) : ProducerMessageHandlerCustomizer<KafkaProducerMessageHandler<String, String>> {
        return ProducerMessageHandlerCustomizer<KafkaProducerMessageHandler<String, String>> {
                handler, destinationName ->
            handler.kafkaTemplate.setObservationEnabled(true)
            handler.kafkaTemplate.setApplicationContext(applicationContext)
            handler.kafkaTemplate.afterSingletonsInstantiated()
        }
    }
}