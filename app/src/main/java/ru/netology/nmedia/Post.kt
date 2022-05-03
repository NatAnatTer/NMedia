package ru.netology.nmedia

import android.graphics.drawable.Icon

class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    var likedByMe: Boolean = false,
    var views: Int = 0,
    var reposts: Int = 0,
    val avatar: Int
)
