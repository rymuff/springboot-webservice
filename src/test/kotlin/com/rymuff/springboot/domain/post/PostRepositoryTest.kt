package com.rymuff.springboot.domain.post

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDateTime

@DataJpaTest
internal class PostRepositoryTest(
    @Autowired private val postRepository: PostRepository,
) {
    @Test
    fun save() {
        // given
        val title = "title"
        val content = "content"
        val author = "author"
        postRepository.save(Post(title, content, author))

        // when
        val postList = postRepository.findAll()

        // then
        val post = postList[0]
        assertThat(post.id).isNotNull
        assertThat(post.title).isEqualTo(title)
        assertThat(post.content).isEqualTo(content)
        assertThat(post.author).isEqualTo(author)
    }

    @Test
    fun auditing() {
        // given
        val now = LocalDateTime.now().minusSeconds(1)
        val title = "title"
        val content = "content"
        val author = "author"
        postRepository.save(Post(title, content, author))

        // when
        val postList = postRepository.findAll()

        // then
        val post = postList[0]
        assertThat(post.createdDate).isAfter(now)
        assertThat(post.modifiedDate).isAfter(now)
    }
}