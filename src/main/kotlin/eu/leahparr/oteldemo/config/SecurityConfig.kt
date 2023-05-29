package eu.leahparr.oteldemo.config

import org.slf4j.Logger
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.netty.http.client.HttpClient

@EnableWebFluxSecurity
@Configuration
class SecurityConfig {

  @Bean
  fun springSecurityFilterChain(
          http: ServerHttpSecurity,
          httpClient: HttpClient,
          logger: Logger
  ): SecurityWebFilterChain {
    http.authorizeExchange { exchange ->
      exchange.anyExchange().permitAll()
    }
    return http.build()
  }

}