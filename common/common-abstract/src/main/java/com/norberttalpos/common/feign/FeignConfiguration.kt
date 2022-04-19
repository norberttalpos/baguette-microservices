package com.norberttalpos.common.feign

import feign.Retryer
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FeignConfiguration {

    private val feignRetryPeriod = 50L
    private val feignRetryMaxPeriod = 1000L
    private val feignRetryMaxAttempts = 10

    @Bean
    fun retryer(): Retryer {
        return Retryer.Default(
            feignRetryPeriod,
            feignRetryMaxPeriod,
            feignRetryMaxAttempts
        )
    }

    @Bean
    fun errorDecoder(): ErrorDecoder {
        return FeignClientErrorDecoder()
    }
}