package com.poc.rate.limit.rate.service

import com.poc.rate.limit.rate.constants.Constants
import io.github.bucket4j.Bandwidth
import io.github.bucket4j.Refill
import java.time.Duration


enum class ApiCapacidade(private val bucketCapacity: Int) {
    API_A(Constants.CAPACIDADE_API_A), API_B(Constants.CAPACIDADE_API_B), API_C(Constants.CAPACIDADE_API_C);

    val limit: Bandwidth
        get() = Bandwidth.classic(
            bucketCapacity.toLong(),
            Refill.intervally(bucketCapacity.toLong(), Duration.ofSeconds( Constants.QUANTIDADE_SEGUNDOS))
        )

    fun bucketCapacity(): Int {
        return bucketCapacity
    }

    companion object {
        fun resolvePlanFromApiKey(apiKey: String?): ApiCapacidade {
            //acesso default
            if (apiKey == null || apiKey.isEmpty()) {
                return API_A
            }
            else if (apiKey.startsWith("PX001-")) {
                return API_C
            } else if (apiKey.startsWith("BX001-")) {
                return API_B
            }
            return API_A
        }
    }
}
