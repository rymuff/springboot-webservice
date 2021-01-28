package com.rymuff.springboot.web.dto

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HelloResponseDtoTest {
    @Test
    fun construction() {
        // given
        val name = "test"
        val amount = 1000

        // when
        val dto = HelloResponseDto(name, amount)

        // then
        assertThat(dto.name).isEqualTo(name)
        assertThat(dto.amount).isEqualTo(amount)
    }
}