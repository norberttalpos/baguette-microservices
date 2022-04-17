package com.norberttalpos.common.feign

import feign.Response
import feign.RetryableException
import feign.codec.ErrorDecoder
import mu.KotlinLogging

class FeignClientErrorDecoder : ErrorDecoder {
    private val defaultErrorDecoder = ErrorDecoder.Default()
    private val logger = KotlinLogging.logger {}

    override fun decode(s: String, response: Response): Exception {
        logger.error { "error in feign request: $s, reason: ${response.status()} ${response.reason()}" }

        return if (response.status() == 503) {
            RetryableException(
                503,
                response.reason(),
                response.request().httpMethod(),
                null,
                response.request()
            )
        } else {
            defaultErrorDecoder.decode(s, response)
        }
    }
}