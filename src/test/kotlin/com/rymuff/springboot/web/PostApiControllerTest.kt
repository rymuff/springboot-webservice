package com.rymuff.springboot.web

import com.rymuff.springboot.domain.post.Post
import com.rymuff.springboot.domain.post.PostRepository
import com.rymuff.springboot.web.dto.PostSaveRequestDto
import com.rymuff.springboot.web.dto.PostUpdateRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.exchange
import org.springframework.boot.test.web.client.postForEntity
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class PostApiControllerTest(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val postRepository: PostRepository,
    @LocalServerPort private val port: Int,
) {
    @AfterEach
    fun tearDown() {
        postRepository.deleteAll()
    }

    @Test
    fun save() {
        // given
        val title = "title"
        val content = "content"
        val author = "author"

        val requestDto = PostSaveRequestDto(title, content, author)
        val url = "http://localhost:$port/api/v1/post"

        // when
        val responseEntity = restTemplate.postForEntity<Long>(url, requestDto)

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0)

        val all = postRepository.findAll()
        assertThat(all[0].title).isEqualTo(title)
        assertThat(all[0].content).isEqualTo(content)
        assertThat(all[0].author).isEqualTo(author)
    }

    @Test
    fun update() {
        // given
        val title = "title"
        val content = "content"
        val author = "author"
        val post = postRepository.save(Post(title, content, author))

        val id = post.id
        val expectedTitle = "title2"
        val expectedContent = "content2"

        val requestDto = PostUpdateRequestDto(expectedTitle, expectedContent)
        val url = "http://localhost:$port/api/v1/post/$id"
        val requestEntity = HttpEntity(requestDto)

        // when
        val responseEntity = restTemplate.exchange<Long>(url, HttpMethod.PUT, requestEntity)

        // then
        assertThat(responseEntity.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(responseEntity.body).isGreaterThan(0)

        val all = postRepository.findAll()
        assertThat(all[0].title).isEqualTo(expectedTitle)
        assertThat(all[0].content).isEqualTo(expectedContent)
    }

    @Test
    fun findById() {
    }
}