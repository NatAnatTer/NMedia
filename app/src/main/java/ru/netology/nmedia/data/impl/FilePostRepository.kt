package ru.netology.nmedia.data.impl


import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.data.PostRepository
import kotlin.properties.Delegates

class FilePostRepository(
    private val application: Application
) : PostRepository {
    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private val prefs: SharedPreferences =
        application.getSharedPreferences("repo", Context.MODE_PRIVATE)

    private var nextId: Long by Delegates.observable(prefs.getLong(NEXT_ID_PREFS_KEYS, 0L))
    { _, _, newValue ->
        prefs.edit {
            putLong(NEXT_ID_PREFS_KEYS, newValue)
        }
    }
    override val data: MutableLiveData<List<Post>>

    init {
        val postsFile = application.filesDir.resolve(FILE_NAME)
        val posts: List<Post> = if (postsFile.exists()) {
            val inputStream = application.openFileInput(FILE_NAME)
            val reader = inputStream.bufferedReader()
            reader.use { gson.fromJson(it, type) }
        } else emptyList()

        data = MutableLiveData(posts)
    }

    private var posts
        get() = checkNotNull(data.value) {
            "value should be not null"
        }
        set(value) {
            application.openFileOutput(FILE_NAME, Context.MODE_PRIVATE).bufferedWriter().use {
                it.write(gson.toJson(value))
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

    override fun getPost(postId: Long) = posts
        .find { it.id == postId }

    companion object {
        const val NEXT_ID_PREFS_KEYS = "nextId"
        const val FILE_NAME = "posts.json"
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