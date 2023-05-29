import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("org.springframework.boot") version "3.1.0"
  id("org.jetbrains.kotlin.plugin.allopen") version "1.8.21"
  id("io.spring.dependency-management") version "1.1.0"
  kotlin("jvm") version "1.8.21"
  id("org.jetbrains.kotlin.plugin.spring") version "1.8.21"
}

group = "eu.leahparr"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
  maven { url = uri("https://repo.spring.io/snapshot") }
  mavenCentral()
}

extra["springCloudVersion"] = "2022.0.3"

dependencyManagement {
  imports {
    mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
  }
}

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("org.springframework.boot:spring-boot-starter-security")
  implementation("org.springframework.boot:spring-boot-starter-webflux")
  implementation("org.springframework.cloud:spring-cloud-starter-gateway")


  implementation("org.springframework.boot:spring-boot-starter-actuator")
  implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
  implementation("ch.qos.logback:logback-classic")
  implementation("io.micrometer:micrometer-docs-generator:1.0.1")
  implementation("io.micrometer:micrometer-tracing-bridge-otel")
  // https://mvnrepository.com/artifact/io.micrometer/context-propagation
  implementation("io.micrometer:context-propagation:1.0.2")

  implementation("io.micrometer:micrometer-registry-otlp")
  implementation("io.opentelemetry:opentelemetry-exporter-otlp")
  implementation("io.opentelemetry:opentelemetry-extension-kotlin:1.25.0")


  implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
  implementation("org.jetbrains.kotlin:kotlin-reflect")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
  implementation("org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
  kotlinOptions {
    freeCompilerArgs = listOf("-Xjsr305=strict")
    jvmTarget = "17"
  }
}

tasks.withType<Test> {
  useJUnitPlatform()
}
