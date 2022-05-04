package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import ru.netology.nmedia.PostViewModel.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { post ->
            binding.render(post)
        }

        binding.postList.like.setOnClickListener {
            viewModel.onLkeClicked()
        }
        binding.postList.reposts.setOnClickListener {
            viewModel.onRepostClicked()
        }
    }

    private fun ActivityMainBinding.render(post1: Post) {
        postList.authorName.text = post1.author
        postList.date.text = post1.published
        post1.content.also { postList.postBody.text = it }
        postList.like.setImageResource(getLikeIconResId(post1.likedByMe))
        postList.likesCount.text = getTextViewCount(post1.likes)
        postList.usersViewsCount.text = getTextViewCount(post1.views)
        postList.repostsCount.text = getTextViewCount(post1.reposts)
        postList.avatar.setImageResource(post1.avatar)
    }

    @DrawableRes
    fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_liked_16 else R.drawable.ic_likes_16

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



