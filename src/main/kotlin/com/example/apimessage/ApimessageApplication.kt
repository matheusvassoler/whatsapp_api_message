package com.example.apimessage

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class ApimessageApplication {
	init {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}

fun main(args: Array<String>) {
	runApplication<ApimessageApplication>(*args)
}
