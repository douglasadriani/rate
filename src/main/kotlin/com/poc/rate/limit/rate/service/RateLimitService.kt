package com.poc.rate.limit.rate.service

import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Bucket
import io.github.bucket4j.Bucket4j
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class RateLimitService {
    private val cache: MutableMap<String, Bucket> = ConcurrentHashMap()

    fun resolveBucket(apiKey: String): Bucket {
        return cache.computeIfAbsent(apiKey)
                {
                        apiKey: String -> newBucket(apiKey)
                }
    }

    private fun newBucket(apiKey: String): Bucket {
        val pricingPlan = ApiCapacidade.resolvePlanFromApiKey(apiKey)
        return bucket(pricingPlan.limit)
    }

    private fun bucket(limit: Bandwidth): Bucket {
        return Bucket4j.builder().addLimit(limit).build()
    }
}
