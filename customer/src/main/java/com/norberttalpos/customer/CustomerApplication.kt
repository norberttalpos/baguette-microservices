package com.norberttalpos.customer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CustomerApplication

fun main(args: Array<String>) {
    runApplication<CustomerApplication>(*args)
}