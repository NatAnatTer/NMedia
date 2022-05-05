package ru.netology.nmedia


data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val views: Int = 0,
    val reposts: Int = 0,
    val avatar: Int
)

