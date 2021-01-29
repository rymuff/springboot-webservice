package com.rymuff.springboot.domain.post

import com.rymuff.springboot.domain.BaseTimeEntity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Post(
    var title: String,
    var content: String,
    val author: String,
    @Id @GeneratedValue val id: Long = 0,
) : BaseTimeEntity() {
    fun update(title: String, content: String) {
        this.title = title
        this.content = content
    }
}