package com.poc.rate.limit.rate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RateApplication

fun main(args: Array<String>) {
	runApplication<RateApplication>(*args)
}
