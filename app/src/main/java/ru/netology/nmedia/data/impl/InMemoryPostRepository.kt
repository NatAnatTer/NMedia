package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    private val posts
        get() = checkNotNull(data.value) {
            "value should be not null"
        }
    override val data = MutableLiveData(
        List(10) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "$index Привет, это новая Нетология! Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                published = "05.05.2022",
                likedByMe = false,
                likes = 1099,
                reposts = 10,
                views = 1000,
                avatar = R.drawable.ic_netology
            )
        }
    )

    override fun like(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                likedByMe = !it.likedByMe,
                likes = getLikesCount(it.likedByMe, it.likes)
            )
        }
    }

    override fun repost(postId: Long) {
        data.value = posts.map {
            if (it.id != postId) it
            else it.copy(
                reposts = it.reposts + 1
            )
        }
    }
}

private fun getLikesCount(liked: Boolean, likes: Int): Int {
    var like: Int = likes
    if (!liked) {
        like = likes + 1
    } else if (liked && likes == 0) {
        like = likes
    } else if (liked && likes > 0) {
        like = likes - 1
    }
    return like
}