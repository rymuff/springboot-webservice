package com.rymuff.springboot.web

import com.rymuff.springboot.service.PostService
import com.rymuff.springboot.web.dto.PostSaveRequestDto
import com.rymuff.springboot.web.dto.PostUpdateRequestDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PostApiController(
    private val postService: PostService,
) {
    @PostMapping("/api/v1/post")
    fun save(@RequestBody requestDto: PostSaveRequestDto) = postService.save(requestDto)

    @PutMapping("/api/v1/post/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostUpdateRequestDto) =
        postService.update(id, requestDto)

    @GetMapping("/api/v1/post/{id}")
    fun findById(@PathVariable id: Long) = postService.findById(id)

    @DeleteMapping("/api/v1/post/{id}")
    fun delete(@PathVariable id: Long) = postService.delete(id)
}