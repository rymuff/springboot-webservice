package com.rymuff.springboot.service

import com.rymuff.springboot.domain.post.PostRepository
import com.rymuff.springboot.web.dto.PostResponseDto
import com.rymuff.springboot.web.dto.PostSaveRequestDto
import com.rymuff.springboot.web.dto.PostUpdateRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PostService(
    private val postRepository: PostRepository,
) {
    fun save(postSaveRequestDto: PostSaveRequestDto) = postRepository.save(postSaveRequestDto.toEntity()).id

    @Transactional
    fun update(id: Long, requestDto: PostUpdateRequestDto): Long {
        val post = postRepository.findById(id).orElseThrow { IllegalArgumentException("Not found id=$id") }
        post.update(requestDto.title, requestDto.content)
        return id
    }

    fun findById(id: Long): PostResponseDto {
        val post = postRepository.findById(id).orElseThrow { IllegalArgumentException("Not found id=$id") }
        return PostResponseDto.of(post)
    }
}