package ru.netology.nmedia.adapter

import ru.netology.nmedia.Post

interface PostInteractionListener {
    fun onLikeClicked(post: Post)
    fun onRepostClicked(post: Post)
    fun onRemoveClicked(post:Post)
    fun onEditClicked(post:Post)

    fun onUndoEditClicked(post: Post)
}