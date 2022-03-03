package com.norberttalpos.tracking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class TrackingApplication

fun main(args: Array<String>) {
    runApplication<TrackingApplication>(*args)
}