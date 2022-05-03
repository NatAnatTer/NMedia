package ru.netology.nmedia

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.DrawableRes
import ru.netology.nmedia.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val postExample = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likedByMe = false,
            likes = 10,
            reposts = 234,
            views = 987,
            avatar = R.drawable.ic_netology
        )
        binding.render(postExample)
        binding.post.like.setOnClickListener {
            postExample.likedByMe = !postExample.likedByMe
            binding.post.like.setImageResource(getLikeIconResId(postExample.likedByMe))
            binding.post.likesCount.text =
                getLikesCount(postExample.likedByMe, postExample.likes, postExample).toString()
        }
        binding.post.reposts.setOnClickListener {
            binding.post.repostsCount.text =
                getRepostsCount(postExample.reposts, postExample)
        }


    }

    private fun ActivityMainBinding.render(post1: Post) {
        post.authorName.text = post1.author
        post.date.text = post1.published
        post1.content.also { post.postBody.text = it }
        post.like.setImageResource(getLikeIconResId(post1.likedByMe))
        post.likesCount.text = post1.likes.toString()
        post.usersViewsCount.text = post1.views.toString()
        post.repostsCount.text = post1.reposts.toString()
        post.avatar.setImageResource(post1.avatar)  //setImageDrawable(Drawable.createFromPath(post1.avatar))
    }

    @DrawableRes
    private fun getLikeIconResId(liked: Boolean) =
        if (liked) R.drawable.ic_liked_16 else R.drawable.ic_likes_16


    private fun getLikesCount(liked: Boolean, likes: Int, post1: Post): Int {
        var like: Int = likes
        if (liked) {like = likes + 1
        }else if (!liked && likes ==0){ like = likes
        }else if (!liked && likes > 0) {
            like = likes -1
        }
        post1.likes = like
        return like
    }

    private fun getRepostsCount(count: Int, post1: Post): String {
        post1.reposts += 1
        return (count + 1).toString()
    }

}

//fun getTextViewCount(count: Int): String {
//    return when (count) {
//        in 0..999 -> count.toString()
//        in 1000..10000 -> (count / 1000).toString() + "K"
//        in 100001..999999 -> (count / 1000)
//    }
//}


