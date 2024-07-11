package com.ahmedmq.tas.playground

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/secrets")
class SecretController(private val env: Environment) {

    @Value("\${secret.message}")
    lateinit var message: String

    @GetMapping("/message")
    fun propertySecret(): String = message
}
