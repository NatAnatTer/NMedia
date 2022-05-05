package ru.netology.nmedia.data.impl

import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostListItemBinding
import java.text.DecimalFormat


internal class PostsAdapter(
    private val onLikeClicked: (Post) -> Unit
) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: PostListItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding) {
            authorName.text = post.author
            date.text = post.published
            post.content.also { postBody.text = it }
            like.setImageResource(getLikeIconResId(post.likedByMe))
            likesCount.text = getTextViewCount(post.likes)
            usersViewsCount.text = getTextViewCount(post.views)
            repostsCount.text = getTextViewCount(post.reposts)
            avatar.setImageResource(post.avatar)
            like.setOnClickListener { viewModel.onLkeClicked(post) }
            reposts.setOnClickListener { viewModel.onRepostClicked(post) }
        }

        @DrawableRes
        fun getLikeIconResId(liked: Boolean) =
            if (liked) R.drawable.ic_liked_16 else R.drawable.ic_likes_16
    }


}

fun getTextViewCount(count: Int): String {
    val df1 = DecimalFormat("#.#")
    return when (count) {
        in 0..999 -> count.toString()
        in 1000..1099 -> (count / 1000).toString() + "K"
        in 1100..9999 -> (df1.format((count / 100).toDouble() / 10.0)).toString() + "K"
        in 10_000..999_999 -> (count / 1000).toString() + "K"
        in 1_000_000..1_099_999 -> (count / 1_000_000).toString() + "M"
        in 1_100_000..999_999_999 -> (df1.format((count / 100_000).toDouble() / 10.0)).toString() + "M"
        else -> "1B"
    }
}