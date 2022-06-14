package ru.netology.nmedia.db

import android.database.Cursor
import ru.netology.nmedia.dto.Post

fun Cursor.toPost() = Post(
    id = getLong(getColumnIndexOrThrow(PostsTable.Column.ID.columnName)),
    author = getString(getColumnIndexOrThrow(PostsTable.Column.AUTHOR.columnName)),
    content = getString(getColumnIndexOrThrow(PostsTable.Column.CONTENT.columnName)),
    published = getString(getColumnIndexOrThrow(PostsTable.Column.PUBLISHED.columnName)),
    likes = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKES.columnName)),
    likedByMe = getInt(getColumnIndexOrThrow(PostsTable.Column.LIKED_BY_ME.columnName)) !=0,
    views = getInt(getColumnIndexOrThrow(PostsTable.Column.VIEWS.columnName)),
    reposts = getInt(getColumnIndexOrThrow(PostsTable.Column.REPOSTS.columnName)),
    avatar = getString(getColumnIndexOrThrow(PostsTable.Column.AVATAR.columnName)),
    videoAttachmentCover = getString(getColumnIndexOrThrow(PostsTable.Column.VIDEO_ATTACHMENT_COVER.columnName)),
    videoAttachmentHeader = getString(getColumnIndexOrThrow(PostsTable.Column.VIDEO_ATTACHMENT_HEADER.columnName)),
    urlVideo = getString(getColumnIndexOrThrow(PostsTable.Column.URL_VIDEO.columnName)),

)



