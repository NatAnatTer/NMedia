package ru.netology.nmedia.postViewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel(), PostInteractionListener {
    private val repository: PostRepository = InMemoryPostRepository()
    val data by repository::data

    override fun onLikeClicked(post: Post) = repository.like(post.id)
    override fun onRepostClicked(post: Post) = repository.repost(post.id)
    override fun onRemoveClicked(post: Post) = repository.delete(post.id)

    fun onSaveButtonClicked(content: String) {
        val newPost = Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            published = "Now",
            avatar = R.drawable.ic_new_avatar_48
        )
        repository.save(newPost)
    }


}

