package com.rymuff.springboot.web.dto

import com.rymuff.springboot.domain.post.Post
import java.time.LocalDateTime

data class PostListResponseDto(
    val id: Long,
    val title: String,
    val author: String,
    val modifiedDate: LocalDateTime,
) {
    companion object {
        fun of(entity: Post) = PostListResponseDto(entity.id, entity.title, entity.author, entity.modifiedDate)
    }
}
