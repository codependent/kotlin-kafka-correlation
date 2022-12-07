package com.codependent.correlation.kafka.kstream

import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.processor.api.Processor
import org.apache.kafka.streams.processor.api.ProcessorSupplier
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.function.Function


/**
 * @author José A. Íñigo
 */
@Configuration
class EventKStreamConfiguration {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Bean
    fun processEvents() =
        Function<KStream<String, String>, KStream<String, String>> { events ->
            events.process(
                ProcessorSupplier {
                    Processor { record ->
                        logger.info("processEvents() -> \nheaders {}  \nkey {} - \nvalue {}",
                            record.headers().map { "\n   ${it.key()} - ${String(it.value())}" },
                            record.key(), record.value()
                        )
                    }
                })
        }
}