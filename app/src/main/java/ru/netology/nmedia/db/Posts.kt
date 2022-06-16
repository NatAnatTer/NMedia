package ru.netology.nmedia.db

import android.database.Cursor
import ru.netology.nmedia.dto.Post

fun PostEntity.toModel() = Post(
    id = id,
    author = author,
    content = content,
    published = published,
    likes = likes,
    likedByMe = likedByMe,
    views = views,
    reposts = reposts,
    avatar = avatar,
    videoAttachmentCover = videoAttachmentCover,
    videoAttachmentHeader = videoAttachmentHeader,
    urlVideo = urlVideo,
)

fun Post.toEntity() = PostEntity(
    id = id,
    author = author,
    content = content,
    published = published,
    likes = likes,
    likedByMe = likedByMe,
    views = views,
    reposts = reposts,
    avatar = avatar,
    videoAttachmentCover = videoAttachmentCover,
    videoAttachmentHeader = videoAttachmentHeader,
    urlVideo = urlVideo,
)



