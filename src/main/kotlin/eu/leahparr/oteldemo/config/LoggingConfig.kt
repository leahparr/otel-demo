package eu.leahparr.oteldemo.config

import io.opentelemetry.context.Context
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InjectionPoint
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.GlobalFilter
import org.springframework.cloud.gateway.filter.factory.rewrite.ModifyResponseBodyGatewayFilterFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.core.Ordered
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Configuration
class LoggingConfig {

  @Bean
  @Scope("prototype")
  fun logger(injectionPoint: InjectionPoint): Logger {
    return LoggerFactory.getLogger(
            injectionPoint.methodParameter?.containingClass // constructor
                    ?: injectionPoint.field?.declaringClass) // or field injection
  }

  @Bean
  fun getGlobalLoggingFilter(
          logger: Logger,
          modifyResponseBodyFilter: ModifyResponseBodyGatewayFilterFactory,
  ): GlobalFilter = object : GlobalFilter, Ordered {
    override fun filter(exchange: ServerWebExchange, chain: GatewayFilterChain): Mono<Void> {
      val method = exchange.request.method
      val path = exchange.request.uri.toString()
      val headers = exchange.request.headers

      logger.info("1111111111111111111111111")
      var responseBody = ""
      return modifyResponseBodyFilter.apply(
              ModifyResponseBodyGatewayFilterFactory.Config()
                      .setRewriteFunction(
                              String::class.java,
                              String::class.java
                      ) { _, body ->
                        responseBody = body
                        Mono.just(responseBody)
                      }
      ).filter(exchange, chain).doOnEach {
        logger.info("Response Body: $responseBody")
        logger.info("Laaassssssssssst")
      }

    }

    override fun getOrder() = Ordered.HIGHEST_PRECEDENCE
  }
}

