package com.norberttalpos.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ProductApplication

fun main(args: Array<String>) {
    runApplication<ProductApplication>(*args)
}