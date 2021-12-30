package com.poc.rate.limit.rate.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/rateLimitNotController")
class RateLimitNotFilterController {
    @GetMapping("/{nome}")
    fun getTestRateLimit(@PathVariable(value = "nome") nome: String): String {
        return "Oi, $nome!\n"
    }
}