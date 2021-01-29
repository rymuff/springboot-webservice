package com.rymuff.springboot.web.dto

import com.rymuff.springboot.domain.post.Post

data class PostSaveRequestDto(
    val title: String,
    val content: String,
    val author: String
) {
    fun toEntity() = Post(title, content, author)
}
