package com.zlargon.springdemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication class SpringdemoApplication

fun main(args: Array<String>) {
  runApplication<SpringdemoApplication>(*args)
}
