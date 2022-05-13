package ru.netology.nmedia.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.nmedia.Post
import ru.netology.nmedia.R
import ru.netology.nmedia.databinding.PostListItemBinding
import java.text.DecimalFormat


internal class PostsAdapter(
    private val interactionListener: PostInteractionListener
) : ListAdapter<Post, PostsAdapter.ViewHolder>(DiffCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding, interactionListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    inner class ViewHolder(
        private val binding: PostListItemBinding,
        private val listener: PostInteractionListener
    ) : RecyclerView.ViewHolder(binding.root) {

        private lateinit var post: Post
        private val popupMenu by lazy {
            PopupMenu(itemView.context, binding.menu).apply {
                inflate(R.menu.options_post)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.remove -> {
                            listener.onRemoveClicked(post)
                            true
                        }
                        R.id.edit -> {
                            listener.onEditClicked(post)
                            true
                        }
                        else -> false
                    }
                }
            }
        }

        init {
            binding.like.setOnClickListener { listener.onLikeClicked(post) }
            binding.reposts.setOnClickListener { listener.onRepostClicked(post) }
            binding.menu.setOnClickListener { popupMenu.show()}
            }

        fun bind(post: Post) {
            this.post = post
            with(binding) {
                authorName.text = post.author
                date.text = post.published
                post.content.also { postBody.text = it }
               // like.setButtonDrawable(getLikeIconResId(post.likedByMe))
                like.text = getTextViewCount(post.likes)
                like.isChecked = post.likedByMe
                usersViews.text = getTextViewCount(post.views)
                reposts.text = getTextViewCount(post.reposts)
                avatar.setImageResource(post.avatar)

            }
        }

//        @DrawableRes
//        fun getLikeIconResId(liked: Boolean) =
//            if (liked) R.drawable.ic_liked_24 else R.drawable.ic_likes_24
    }

    private object DiffCallBack : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem

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

//<androidx.appcompat.widget.AppCompatImageButton
//android:id="@+id/users_views"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="@dimen/margin_top"
//android:layout_marginEnd="4dp"
//android:background="@android:color/transparent"
//android:contentDescription="@string/views"
//android:src="@drawable/ic_views_16"
//app:layout_constraintEnd_toStartOf="@id/users_views_count"
//app:layout_constraintTop_toBottomOf="@id/body_barrier" />
//
//<androidx.appcompat.widget.AppCompatTextView
//android:id="@+id/users_views_count"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:layout_marginTop="@dimen/margin_top"
//android:ellipsize="end"
//android:singleLine="true"
//android:textSize="14sp"
//app:layout_constraintEnd_toEndOf="parent"
//app:layout_constraintTop_toBottomOf="@id/body_barrier"
//tools:ignore="MissingConstraints,SmallSp"
//tools:layout_editor_absoluteX="16dp"
//tools:text="5324" />


//<androidx.appcompat.widget.AppCompatImageButton
//android:id="@+id/reposts"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginStart="16dp"
//android:layout_marginTop="@dimen/margin_top"
//android:background="@android:color/transparent"
//android:contentDescription="@string/share"
//android:src="@drawable/ic_share_24"
//app:layout_constraintStart_toEndOf="@id/like"
//app:layout_constraintTop_toBottomOf="@id/body_barrier" />
//
//<androidx.appcompat.widget.AppCompatTextView
//android:id="@+id/reposts_count"
//android:layout_width="0dp"
//android:layout_height="wrap_content"
//android:layout_marginStart="4dp"
//android:layout_marginTop="@dimen/margin_top"
//
//android:textSize="14sp"
//app:layout_constraintStart_toEndOf="@id/reposts"
//app:layout_constraintTop_toBottomOf="@id/body_barrier"
//tools:ignore="MissingConstraints,SmallSp"
//tools:layout_editor_absoluteX="16dp"
//tools:text="5" />
//<com.google.android.material.button.MaterialButton
//android:id="@+id/like"
//style="@style/Widget.AppTheme.LikeCheckbox"
//android:layout_width="wrap_content"
//android:layout_height="wrap_content"
//android:checkable="true"
//android:contentDescription="@string/likes"
//app:layout_constraintStart_toStartOf="parent"
//app:layout_constraintTop_toBottomOf="@id/body_barrier"
//tools:text="10700"
///>