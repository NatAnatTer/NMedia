package ru.netology.nmedia.postViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.R
import ru.netology.nmedia.adapter.PostInteractionListener
import ru.netology.nmedia.adapter.PostShowDetailInteractionListener
import ru.netology.nmedia.data.PostRepository
import ru.netology.nmedia.data.impl.FilePostRepository
import ru.netology.nmedia.dto.Post
import ru.netology.nmedia.util.SingleLiveEvent

class PostShowDetailViewModel(application: Application): AndroidViewModel(application), PostShowDetailInteractionListener {
    private val repository: PostRepository = FilePostRepository(application)
    val data by repository::data
    val currentPost = MutableLiveData<Post?>(null)
    val sharePostContent = SingleLiveEvent<String>()
    val videoLinkPlay = SingleLiveEvent<String>()
    val navigateToPostContentScreenEvent = SingleLiveEvent<String>()

    override   fun onLikeClicked(){} //= repository.like()

    override  fun onRepostClicked() {
//        sharePostContent.value = context.content
//        repository.repost(post.id)
    }

    override  fun onRemoveClicked(){} //= repository.delete()

    override  fun onEditClicked() {
//        currentPost.value = post
//        navigateToPostContentScreenEvent.value = post.content
    }

    override fun onPlayVideoClicked() {
//        videoLinkPlay.value = post.urlVideo!!
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
            avatar = R.drawable.ic_new_avatar_48,
            videoAttachmentCover = null,
            videoAttachmentHeader = null,
            urlVideo = null
        )
        repository.save(newPost)
        currentPost.value = null
    }


}

