package com.rymuff.springboot.web

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@ExtendWith(SpringExtension::class)
@WebMvcTest(controllers = [HelloController::class])
@MockBean(JpaMetamodelMappingContext::class)
internal class HelloControllerTest(
    @Autowired private val mockMvc: MockMvc,
) {
    @Test
    fun hello() {
        val hello = "hello"

        mockMvc.perform(get("/hello"))
            .andExpect(status().isOk)
            .andExpect(content().string(hello))
    }

    @Test
    fun helloDto() {

        val name = "test"
        val amount = 1000

        mockMvc.perform(
            get("/hello/dto")
                .param("name", name)
                .param("amount", amount.toString())
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.name").value(name))
            .andExpect(jsonPath("$.amount").value(amount))
    }
}