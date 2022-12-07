package com.codependent.correlation.tracing

import io.micrometer.context.ContextSnapshot
import io.micrometer.core.instrument.kotlin.asContextElement
import io.micrometer.observation.ObservationRegistry
import io.micrometer.observation.contextpropagation.ObservationThreadLocalAccessor
import kotlinx.coroutines.reactor.ReactorContext
import kotlinx.coroutines.withContext
import reactor.core.publisher.Mono
import reactor.util.context.ContextView
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

suspend fun <T> withObservationContext(observationRegistry: ObservationRegistry, f: suspend () -> T): T {
    return withContext(generateObservationContextElement(observationRegistry)) {
        f()
    }
}

suspend fun generateObservationContextElement(observationRegistry: ObservationRegistry): CoroutineContext {
    return ContextSnapshot.setThreadLocalsFrom(
        coroutineContext[ReactorContext]!!.context,
        ObservationThreadLocalAccessor.KEY
    ).use {
        observationRegistry.asContextElement()
    }
}