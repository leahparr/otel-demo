package eu.leahparr.oteldemo.config

import eu.leahparr.oteldemo.service.TestService
import io.opentelemetry.context.Context
import io.opentelemetry.extension.kotlin.asContextElement
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.server.ServerWebExchange

@Configuration
class GlobalFilterConfig(
        private val logger: Logger,
        private val testService: TestService) {

  @Bean
  fun customGlobalFilter(logger: Logger) = GlobalFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->

    logger.info("33333333333333333333333")
    testService.getFunnyLines()
            .map {
              logger.info(it)
            }.flatMap {
              mono(Context.current().asContextElement()) {
                testService.getFunnyLines2().map {
                  logger.info(it)
                }
              }
            }
            .flatMap {
              chain.filter(exchange)
            }
  }


}