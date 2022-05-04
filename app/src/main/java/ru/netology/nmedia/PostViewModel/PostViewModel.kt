package ru.netology.nmedia.PostViewModel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.InMemoryPostRepository

class PostViewModel : ViewModel() {
    private val repository: PostRepository = InMemoryPostRepository()


    val data = repository.get()
   // val data by repository::data

    fun onLkeClicked() = repository.like()
    fun onRepostClicked() = repository.repost()
}