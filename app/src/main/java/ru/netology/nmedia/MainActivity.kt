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


//        val postExample = Post(
//            id = 1,
//            author = "Нетология. Университет интернет-профессий будущего",
//            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
//            published = "21 мая в 18:36",
//            likedByMe = false,
//            likes = 999,
//            reposts = 999,
//            views = 1000,
//            avatar = R.drawable.ic_netology
//        )
        viewModel.data.observe(this) { post ->
            //binding.render(post)
            with(binding) {
                authorName.text = post.author
                date.text = post.published
                post.content.also { postBody.text = it }
                like.setImageResource(getLikeIconResId(post.likedByMe))
                likesCount.text = getTextViewCount(post.likes)
                usersViewsCount.text = getTextViewCount(post.views)
                repostsCount.text = getTextViewCount(post.reposts)
                avatar.setImageResource(post.avatar)
            }
        }

//        binding.post.like.setOnClickListener {
//            viewModel.onLkeClicked()


//            postExample.likedByMe = !postExample.likedByMe
//            binding.post.like.setImageResource(getLikeIconResId(postExample.likedByMe))
//            binding.post.likesCount.text =
//                getLikesCount(postExample.likedByMe, postExample.likes, postExample)
//        }
//        binding.post.reposts.setOnClickListener {
//            viewModel.onRepostClicked()
//


//            binding.post.repostsCount.text =
//                getRepostsCount(postExample.reposts, postExample)
//        }
    }

    private fun ActivityMainBinding.render(post1: Post) {
        authorName.text = post1.author
        date.text = post1.published
        post1.content.also { postBody.text = it }
        like.setImageResource(getLikeIconResId(post1.likedByMe))
        likesCount.text = getTextViewCount(post1.likes)
        usersViewsCount.text = getTextViewCount(post1.views)
        repostsCount.text = getTextViewCount(post1.reposts)
        avatar.setImageResource(post1.avatar)
    }
//    private fun ActivityMainBinding.render(post1: Post) {
//        postList.authorName.text = post1.author
//        postList.date.text = post1.published
//        post1.content.also { postList.postBody.text = it }
//        postList.like.setImageResource(getLikeIconResId(post1.likedByMe))
//        postList.likesCount.text = getTextViewCount(post1.likes)
//        postList.usersViewsCount.text = getTextViewCount(post1.views)
//        postList.repostsCount.text = getTextViewCount(post1.reposts)
//        postList.avatar.setImageResource(post1.avatar)
//    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_liked_16 else R.drawable.ic_likes_16


//    private fun getLikesCount(liked: Boolean, likes: Int, post1: Post): String {
//        var like: Int = likes
//        if (liked) {
//            like = likes + 1
//        } else if (!liked && likes == 0) {
//            like = likes
//        } else if (!liked && likes > 0) {
//            like = likes - 1
//        }
//        post1.likes = like
//
//        return getTextViewCount(like)
//    }

//    private fun getRepostsCount(count: Int, post1: Post): String {
//        post1.reposts += 1
//        return getTextViewCount((count + 1))
//    }

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


