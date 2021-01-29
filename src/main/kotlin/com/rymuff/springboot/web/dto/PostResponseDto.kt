package com.rymuff.springboot.web.dto

import com.rymuff.springboot.domain.post.Post

data class PostResponseDto(
    val id: Long,
    val title: String,
    val content: String,
    val author: String
) {
    companion object {
        fun of(entity: Post) = PostResponseDto(entity.id, entity.title, entity.content, entity.author)
    }
}
