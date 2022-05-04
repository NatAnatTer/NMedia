package ru.netology.nmedia.data.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {
    private var post = Post(
        id = 1,
        author = "Нетология. Университет интернет-профессий будущего",
        content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
        published = "21 мая в 18:36",
        likes = 999,
        reposts = 999,
        views = 1000,
        avatar = R.drawable.ic_netology
    )
    private val data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
//    override val data = MutableLiveData(
//        Post(
//            id = 1,
//            author = "Нетология. Университет интернет-профессий будущего",
//            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
//            published = "21 мая в 18:36",
//            likedByMe = false,
//            likes = 999,
//            reposts = 999,
//            views = 1000,
//            avatar = R.drawable.ic_netology
//        )
//    )


    override fun like() {
        val currentPost = checkNotNull(data.value) {
            "value should be not null"
        }
        val countLike = currentPost.likes
        val likedPost = if (currentPost.likedByMe) currentPost.copy(
            likedByMe = !currentPost.likedByMe,
            likes = (countLike - 1)
        ) else currentPost.copy(
            likedByMe = !currentPost.likedByMe,
            likes = (countLike + 1)
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