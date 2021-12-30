package com.poc.rate.limit.rate.constants

object Constants {
    const val QUANTIDADE_SEGUNDOS :Long = 70
    const val RATE_LIMIT_CONTROLLER_INTERCEPTOR = "/rateLimitController/**"
    const val PACKAGE_SCAN = "com.poc.rate.limit.rate"
    const val HEADER_API_KEY = "X-api-key"
    const val HEADER_LIMIT_REMAINING = "X-Rate-Limit-Remaining"
    const val HEADER_RETRY_AFTER = "X-Rate-Limit-Retry-After-Seconds"

    const val CAPACIDADE_API_A :Int = 20
    const val CAPACIDADE_API_B :Int = 40
    const val CAPACIDADE_API_C :Int = 80
}
