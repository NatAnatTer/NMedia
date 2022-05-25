package ru.netology.nmedia.data.impl


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.data.PostRepository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.properties.Delegates

class SharedPrefsPostRepository(
    application: Application
) : PostRepository {

   // val prefs = application.getSharedPreferences("repo", Context.MODE_PRIVATE)
    val prefs: SharedPreferences = application.getSharedPreferences("repo", Context.MODE_PRIVATE)
    private var nextId: Long by Delegates.observable(prefs.getLong(NEXT_ID_PREFS_KEYS, 0L)){
        _, _, newValue ->
        prefs.edit {
            putLong(NEXT_ID_PREFS_KEYS, newValue)
        }
    }

    //    override val data = MutableLiveData(
//        List(GENERATED_POST_AMOUNT) { index ->
//            Post(
//                id = index + 1L,
//                author = "Нетология. Университет интернет-профессий будущего",
//                content = "$index Привет, это новая Нетология! Наша миссия — помочь встать на путь роста и начать цепочку перемен → https://www.youtube.com/watch?v=WhWc3b3KhnY",
//                published = "05.05.2022",
//                likedByMe = false,
//                likes = 1099,
//                reposts = 10,
//                views = 1000,
//                avatar = R.drawable.ic_netology,
//                videoAttachmentCover = R.drawable.cat,
//                videoAttachmentHeader = "New video about my beautiful kat",
//                urlVideo = "https://www.youtube.com/watch?v=WhWc3b3KhnY"
//            )
//        }
//    )
    override val data: MutableLiveData<List<Post>>

    init {
        val serializedPost = prefs.getString(POSTS_PREFS_KEYS, null)
        val posts: List<Post> = if (serializedPost != null) {
            Json.decodeFromString(serializedPost)
        } else emptyList()
        data = MutableLiveData(posts)
    }

    private var posts
        get() = checkNotNull(data.value) {
            "value should be not null"
        }
        set(value) {
            val serializedPosts = Json.encodeToString(value)
            prefs.edit {
                putString(POSTS_PREFS_KEYS, serializedPosts)
            }
            data.value = value
        }

    override fun like(postId: Long) {
        posts = posts.map {
            if (it.id != postId) it
            else it.copy(
                likedByMe = !it.likedByMe,
                likes = getLikesCount(it.likedByMe, it.likes)
            )
        }
    }

    override fun repost(postId: Long) {
        posts = posts.map {
            if (it.id != postId) it
            else it.copy(
                reposts = it.reposts + 1
            )
        }
    }

    override fun delete(postId: Long) {
        posts = posts.filterNot { it.id == postId }
    }

    override fun save(post: Post) {
        if (post.id == PostRepository.NEW_POST_ID) insert(post) else update(post)

    }

    private fun update(post: Post) {
        posts = posts.map {
            if (it.id == post.id) post else it
        }
    }

    private fun insert(post: Post) {
        posts = listOf(post.copy(id = ++nextId)) + posts
    }

    private companion object {
        const val GENERATED_POST_AMOUNT = 3
        const val POSTS_PREFS_KEYS = "posts"
        const val NEXT_ID_PREFS_KEYS = "posts"
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