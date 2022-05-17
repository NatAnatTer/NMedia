package ru.netology.nmedia.postViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepository
import ru.netology.nmedia.util.SingleLiveEvent

class PostViewModel : ViewModel(), PostInteractionListener {
    private val repository: PostRepository = InMemoryPostRepository()
    val data by repository::data
    private val currentPost = MutableLiveData<Post?>(null)

    val sharePostContent = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<Unit>()

    fun onAddButtonClicked() {
        navigateToPostContentScreenEvent.call()
    }


    override fun onLikeClicked(post: Post) = repository.like(post.id)
    override fun onRepostClicked(post: Post) {
        sharePostContent.value = post.content
        repository.repost(post.id)
    }

    override fun onRemoveClicked(post: Post) = repository.delete(post.id)
    override fun onEditClicked(post: Post) {
        currentPost.value = post
    }

    override fun onUndoEditClicked(post: Post) {
        currentPost.value = null
    }


    fun onSaveButtonClicked(content: String) {
        if (content.isBlank()) return

        val newPost = currentPost.value?.copy(
            content = content
        ) ?: Post(
            id = PostRepository.NEW_POST_ID,
            author = "Me",
            content = content,
            published = "Now",
            avatar = R.drawable.ic_new_avatar_48
        )
        repository.save(newPost)
        currentPost.value = null
    }
}

