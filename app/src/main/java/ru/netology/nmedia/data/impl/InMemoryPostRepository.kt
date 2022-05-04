package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    override val data = MutableLiveData(
        Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 1099,
            reposts = 10,
            views = 1000,
            avatar = R.drawable.ic_netology
        )
    )

    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "value should be not null"
        }
        val likedPost = currentPost.copy(
            likedByMe = !currentPost.likedByMe,
            likes = getLikesCount(currentPost.likedByMe, currentPost.likes)
        )

        data.value = likedPost
    }

    override fun repost() {
        val currentPost = checkNotNull(data.value) {
            "value should be not null"
        }
        val countRepost = currentPost.reposts
        val repostedPost = currentPost.copy(reposts = countRepost + 1)
        data.value = repostedPost
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