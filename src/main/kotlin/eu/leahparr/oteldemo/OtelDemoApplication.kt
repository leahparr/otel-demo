package eu.leahparr.oteldemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
class OtelDemoApplication

fun main(args: Array<String>) {
	Hooks.enableAutomaticContextPropagation()
	runApplication<OtelDemoApplication>(*args)
}
