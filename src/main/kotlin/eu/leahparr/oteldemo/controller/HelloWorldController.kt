package eu.leahparr.oteldemo.controller

import io.opentelemetry.context.Context
import io.opentelemetry.extension.kotlin.asContextElement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import kotlin.coroutines.coroutineContext

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