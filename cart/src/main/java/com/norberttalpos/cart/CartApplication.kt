package com.norberttalpos.cart

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class CartApplication

fun main(args: Array<String>) {
    runApplication<CartApplication>(*args)
}