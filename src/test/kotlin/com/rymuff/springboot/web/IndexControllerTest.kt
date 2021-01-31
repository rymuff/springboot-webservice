package com.rymuff.springboot.web

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForObject
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class IndexControllerTest(
    @Autowired private val restTemplate: TestRestTemplate,
) {

    @Test
    fun index() {
        // when
        val body = restTemplate.getForObject<String>("/")

        // then
        assertThat(body).contains("Web Service with Spring Boot")
    }
}