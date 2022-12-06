package com.zlargon.springdemo

import org.flywaydb.core.Flyway
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext
import org.testcontainers.containers.PostgreSQLContainer

// https://www.baeldung.com/spring-dynamicpropertysource#an-alternative-test-fixtures
class PostgreSQLExtension : BeforeAllCallback, AfterAllCallback {
  private val DB_DATABASE = "postgres"
  private val DB_USERNAME = "postgres"
  private val DB_PASSWORD = "postgres"
  private val DB_SCHEMA = "spring_demo"

  @Throws(InterruptedException::class)
  override fun beforeAll(context: ExtensionContext) {
    // create postgres container
    val container =
      PostgreSQLContainer("postgres:14-alpine")
        .withUrlParam("currentSchema", DB_SCHEMA)
        .withDatabaseName(DB_DATABASE)
        .withUsername(DB_USERNAME)
        .withPassword(DB_PASSWORD)

    // start postgres container
    container.start()

    // get postgres container port number and JDBC URL
    val DB_PORT = container.firstMappedPort
    val JDBC_URL =
      String.format(
        "jdbc:postgresql://localhost:%d/%s?currentSchema=%s",
        DB_PORT,
        DB_DATABASE,
        DB_SCHEMA
      )
    println("JDBC_URL = $JDBC_URL")
    println("DB_PORT = $DB_PORT")

    // override the application properties
    System.setProperty("spring.datasource.url", JDBC_URL)
    System.setProperty("spring.datasource.username", DB_USERNAME)
    System.setProperty("spring.datasource.password", DB_PASSWORD)

    // wait for postgres container up before doing flyway migration
    Thread.sleep(8000)

    // flyway data migration
    Flyway.configure().dataSource(JDBC_URL, DB_USERNAME, DB_PASSWORD).load().migrate()
  }

  override fun afterAll(context: ExtensionContext) {
    // do nothing, Testcontainers handles container shutdown
  }
}
