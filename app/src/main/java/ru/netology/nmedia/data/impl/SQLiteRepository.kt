package ru.netology.nmedia.data.impl

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.db.PostDao
import ru.netology.nmedia.dto.Post

class SQLiteRepository(
    private val dao: PostDao
) : PostRepository {

    private val posts
        get() = checkNotNull(data.value) {
            "value should be not null"
        }

    override val data = MutableLiveData(dao.getAll())

    override fun like(postId: Long) {
        dao.likeById(postId)
        data.value = posts.map {
            if (it.id != postId) it else it.copy(
                likedByMe = !it.likedByMe,
                likes = if (it.likedByMe) it.likes - 1 else it.likes + 1
            )
        }

    }

    override fun delete(postId: Long) {
        dao.removeById(postId)
        data.value = posts.filter { it.id !=postId }
    }

    override fun save(post: Post) {
        val id = post.id
        val saved = dao.save(post)
        data.value = if (id == 0L) {
            listOf(saved) + posts
        } else {
            posts.map {
                if (it.id != id) it else saved
            }
        }

    }

    override fun getPost(postId: Long): Post? {
        TODO("Not yet implemented")
    }

    override fun repost(postId: Long) {
        TODO("Not yet implemented")
    }


}