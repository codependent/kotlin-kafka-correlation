package com.codependent.correlation.web

import com.codependent.correlation.kafka.producer.EventProducer
import com.codependent.correlation.tracing.withObservationContext
import io.micrometer.observation.ObservationRegistry
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(
    private val eventProducer: EventProducer,
    private val observationRegistry: ObservationRegistry
) {

    @GetMapping("/publish")
    suspend fun publishEvent() {
        withObservationContext(observationRegistry) {
            eventProducer.send(RandomStringUtils.randomAlphanumeric(10), RandomStringUtils.randomAlphanumeric(10))
        }
    }

}