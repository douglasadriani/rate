package com.poc.rate.limit.rate.interceptor

import com.poc.rate.limit.rate.constants.Constants
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.context.annotation.Lazy;

@SpringBootApplication(
    scanBasePackages = [Constants.PACKAGE_SCAN],
    exclude = [DataSourceAutoConfiguration::class, SecurityAutoConfiguration::class]
)
class Bucket4jRateLimitAddInterceptor : WebMvcConfigurer {

    @Autowired
    @Lazy
    private val interceptor: RateLimitFilterInterceptor? = null

    override fun addInterceptors(registry: InterceptorRegistry) {
        if (interceptor != null) {
            registry.addInterceptor(interceptor).addPathPatterns(Constants.RATE_LIMIT_CONTROLLER_INTERCEPTOR)
        }
    }
}