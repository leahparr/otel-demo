package eu.leahparr.oteldemo.config

import io.micrometer.tracing.otel.bridge.BaggageTaggingSpanProcessor
import org.slf4j.Logger
import org.springframework.boot.actuate.autoconfigure.tracing.TracingProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OtelConfig(val logger: Logger) {

  @Bean
  fun baggageTagingSpanProcessor(tracingProperties: TracingProperties): BaggageTaggingSpanProcessor {
    return BaggageTaggingSpanProcessor(tracingProperties.baggage.correlation.fields)
  }

}