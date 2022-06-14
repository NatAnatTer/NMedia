package ru.netology.nmedia.dto


import kotlinx.serialization.Serializable

@Serializable
data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val views: Int = 0,
    val reposts: Int = 0,
    val avatar: Int,
    val videoAttachmentCover: Int?,
    val videoAttachmentHeader: String?,
    val urlVideo: String?
)



