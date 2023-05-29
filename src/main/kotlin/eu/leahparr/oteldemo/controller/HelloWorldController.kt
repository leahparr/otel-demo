package eu.leahparr.oteldemo.controller

import io.opentelemetry.context.Context
import org.slf4j.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloWorldController(
        private val logger: Logger
) {

  @GetMapping("/hello")
  suspend fun helloWorld(): String {

    logger.info("Before world, ${Context.current()}")
    logger.info("Hello world")
    return "Hello"

  }
}