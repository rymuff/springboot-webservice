package com.rymuff.springboot.web

import com.rymuff.springboot.service.PostService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class IndexController(
    private val postService: PostService,
) {
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute("posts", postService.findAll())

        return "index"
    }

    @GetMapping("/post/update/{id}")
    fun postUpdate(@PathVariable id: Long, model: Model): String {
        val dto = postService.findById(id)
        model.addAttribute("post", dto)

        return "post-update"
    }

    @GetMapping("/post/save")
    fun postSave() = "post-save"
}