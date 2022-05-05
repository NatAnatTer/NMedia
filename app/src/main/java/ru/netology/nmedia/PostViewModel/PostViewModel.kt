package ru.netology.nmedia.PostViewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.Post
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()
    val data by repository::data

    fun onLkeClicked(post: Post) = repository.like(post.id)
    fun onRepostClicked(post: Post) = repository.repost(post.id)
}

