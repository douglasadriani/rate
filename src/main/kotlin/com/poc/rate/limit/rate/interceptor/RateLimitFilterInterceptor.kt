package com.poc.rate.limit.rate.interceptor

import com.poc.rate.limit.rate.constants.Constants
import com.poc.rate.limit.rate.service.RateLimitService
import org.apache.tomcat.util.codec.binary.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class RateLimitFilterInterceptor : HandlerInterceptor {
    @Autowired
    private val rateLimitService: RateLimitService? = null
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val apiKey :String = request.getHeader(Constants.HEADER_API_KEY)
        if (apiKey == null || apiKey.isNullOrEmpty() ) {
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Missing Header: " + Constants.HEADER_API_KEY)
            return false
        }

        val tokenBucket = rateLimitService!!.resolveBucket(apiKey)
        val probe = tokenBucket.tryConsumeAndReturnRemaining(1)
        return if (probe.isConsumed) {
            response.addHeader(Constants.HEADER_LIMIT_REMAINING, probe.remainingTokens.toString())
            true
        } else {
            val waitForRefill = probe.nanosToWaitForRefill / 1000000000
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.addHeader(Constants.HEADER_RETRY_AFTER, waitForRefill.toString())
            response.sendError(HttpStatus.TOO_MANY_REQUESTS.value(), "You have exhausted your API Request Quota") // 429
            false
        }
    }
}