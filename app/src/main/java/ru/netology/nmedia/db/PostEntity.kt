package ru.netology.nmedia.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.netology.nmedia.R

@Entity(tableName = "posts")
 class PostEntity(
   @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
   val author: String,
   val content: String,
   val published: String,
   val likes: Int = 0,
   @ColumnInfo(name = "liked_by_me")
    val likedByMe: Boolean = false,
   val views: Int = 0,
   val reposts: Int = 0,
   val avatar: Int = R.drawable.ic_new_avatar_48,
   val videoAttachmentCover: String?,
   val videoAttachmentHeader: String?,
   val urlVideo: String?
)