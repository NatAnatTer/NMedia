package ru.netology.nmedia.data.impl


import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.data.PostRepository

class InMemoryPostRepository : PostRepository {

    private var nextId = GENERATED_POST_AMOUNT.toLong()

    override val data = MutableLiveData(
        List(GENERATED_POST_AMOUNT) { index ->
            Post(
                id = index + 1L,
                author = "Нетология. Университет интернет-профессий будущего",
                content = "$index Привет, это новая Нетология! Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
                published = "05.05.2022",
                likedByMe = false,
                likes = 1099,
                reposts = 10,
                views = 1000,
                avatar = R.drawable.ic_netology,
                videoAttachmentCover = R.drawable.cat,
                videoAttachmentHeader = "New video about my beautiful kat"
            )
        }
    )
    private val posts
        get() = checkNotNull(data.value) {
            "value should be not null"
        }

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

    override fun delete(postId: Long) {
        data.value = posts.filterNot { it.id == postId }
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)

    }

    private fun update(post: Post) {
        data.value = posts.map {
            if (it.id == post.id) post else it
        }
    }

    private fun insert(post: Post) {
        data.value = listOf(post.copy(id = ++nextId)) + posts
    }

    private companion object {
        const val GENERATED_POST_AMOUNT = 3
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