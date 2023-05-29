package eu.leahparr.oteldemo.service

import io.opentelemetry.context.Context
import io.opentelemetry.extension.kotlin.asContextElement
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import kotlinx.coroutines.withContext
import org.slf4j.Logger
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class TestService(
        private val logger: Logger
) {

  fun getFunnyLines(): Mono<String> {
    logger.info("heeyyyaaa")
    return mono(Context.current().asContextElement()) {
      logger.info("filterino")

      "It's funny"
    }
  }

  suspend fun getFunnyLines2() = runCatching {
    logger.info("heyoooooooooooooooooooooooo")
    withContext(Context.current().asContextElement()) {
      Mono.just("Filterinaaaaaaaaaaaaaaaaa").awaitSingle()
    } 
  }
}