package com.camelroute.camel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CamelApplication

fun main(args: Array<String>) {
	runApplication<CamelApplication>(*args)
}
