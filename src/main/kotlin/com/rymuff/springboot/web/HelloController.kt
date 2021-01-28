package com.rymuff.springboot.web

import com.rymuff.springboot.web.dto.HelloResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/hello")
    fun hello() = "hello"

    @GetMapping("/hello/dto")
    fun helloDto(@RequestParam("name") name: String, @RequestParam("amount") amount: Int) =
        HelloResponseDto(name, amount)
}