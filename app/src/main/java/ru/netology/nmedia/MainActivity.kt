package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.DrawableRes
import ru.netology.nmedia.PostViewModel.PostViewModel
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.PostListItemBinding
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<PostViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.data.observe(this) { posts ->
            binding.render(posts)
        }

//        binding.postList.like.setOnClickListener {
//            viewModel.onLkeClicked()
//        }
//        binding.postList.reposts.setOnClickListener {
//            viewModel.onRepostClicked()
//        }
//        <include
//        android:id="@+id/postList"
//        layout="@layout/post_list_item" />
    }

//    private fun ActivityMainBinding.render(posts: List<Post>) {
//        for (post in posts) {
//            PostListItemBinding.inflate(layoutInflater, root, true
//            ).render(post)
//        }
//
//    }

//    private fun PostListItemBinding.render(post1: Post) {
//        authorName.text = post1.author
//        date.text = post1.published
//        post1.content.also { postBody.text = it }
//        like.setImageResource(getLikeIconResId(post1.likedByMe))
//        likesCount.text = getTextViewCount(post1.likes)
//        usersViewsCount.text = getTextViewCount(post1.views)
//        repostsCount.text = getTextViewCount(post1.reposts)
//        avatar.setImageResource(post1.avatar)
//      //  like.setOnClickListener { viewModel.onLkeClicked(post) }
//       // reposts.setOnClickListener { viewModel.onRepostClicked(post) }
//    }

//    @DrawableRes
//    fun getLikeIconResId(liked: Boolean) =
//        if (liked) R.drawable.ic_liked_16 else R.drawable.ic_likes_16

}





